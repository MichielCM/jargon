package jargon.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.ArrayUtils;

import jargon.core.Console;
import jargon.model.Source;
import jargon.model.folia.FoLiA;
import jargon.model.folia.FoliaUtils;
import jargon.model.folia.FoliaWrapper;
import jargon.utils.TimeOut;
import jargon.utils.autocorrector.AutoCorrector;
import jargon.utils.fuzzymatcher.CSVResource;
import jargon.utils.fuzzymatcher.FuzzyMatcher;
import jargon.core.RuleEngine.ResourceType;

public class Pipeline {

	public enum Segments {
		FULL, SENTENCES, TOKENS
	}
	
	private Source source;
	
	public Pipeline(Source source) {
		this.source = source;
	}
	
	public Source getSource() {
		return this.source;
	}
	
	public Pipeline segmentize(Segments segmentType) {
		try {
			this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).set(this.source, this._segmentize(segmentType));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console.log("SEGMENTATION DONE");
		return this;
	}
	
	private String[] _segmentize(Segments segmentType) {
		switch(segmentType) {
			case SENTENCES:
				//return this.source.full[0].split("(?<=[.,—?!;\\n])+");
				return this.source.full[0].split("[.,—?!;\\n]+");
			case TOKENS:
				return this.source.full[0].split("\b");
			default:
				return null;
		}
	}
	
	public Pipeline clean(Segments segmentType) {
		try {
			this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).set(this.source, this._clean(segmentType));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console.log("CLEANING DONE");
		return this;
	}
	
	private String[] _clean(Segments segmentType) {
		try {
			ArrayList<String> segments = new ArrayList<String>();
			
			for (String segment : (String[]) this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source)) {
				segment = segment.trim();
				
				if (segment.length() > 0) {
					if (!segment.split("\\b")[0].toUpperCase().equals(segment.split("\\b")[0]))
						if (segment.substring(0,1).equals(segment.substring(0,1).toUpperCase()))
							segment = segment.substring(0,1).toLowerCase().concat(segment.substring(1));
					
					if (segment.substring(segment.length() - 1).matches("[.,—?!;]"))
						segment = segment.substring(0, segment.length() - 1);
					
					segments.add(segment);
				}
			}
			
			return segments.toArray(new String[segments.size()]);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Pipeline unabbreviate(Segments segmentType) {
		try {
			this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).set(this.source, this._unabbreviate(segmentType));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console.log("UNABBREVIATION DONE");
		return this;
	}
	
	private String[] _unabbreviate(Segments segmentType) {
		try {
			String[] segments = (String[])this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source);
			
			for (int i=0; i<segments.length; i++) {
				AutoCorrector autoCorrector = new AutoCorrector();
				segments[i] = autoCorrector.autoCorrectRegEx(segments[i], "unabbreviation");
				//segments[i] = autoCorrector.autoCorrect(segments[i], "UNABBREVIATION");
			}
			return segments;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Pipeline spellcheck(Segments segmentType) {
		try {
			this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).set(this.source, this._spellcheck(segmentType));
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Console.log("SPELLCHECKING DONE");
		return this;
	}
	
	private String[] _spellcheck(Segments segmentType) {
		try {
			String[] segments = (String[])this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source);
			
			for (int i=0; i<segments.length; i++) {
				AutoCorrector autoCorrector = new AutoCorrector();
				segments[i] = autoCorrector.autoCorrect(segments[i], "TYPOS");
			}
			
			return segments;
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Pipeline frog(Segments segmentType) {
		//try {
			//this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).set(this.source, this._frog(segmentType));
			this._frog(segmentType);
		/*} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		Console.log("FROGGING DONE");
		return this;
	}
	
	private FoLiA[] _frog(Segments segmentType) {
		PrintWriter printWriter = null;
		InputStream inputStream = null;
		
		try {
			String[] segments = (String[])this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source);
			Socket socket = Singleton.getInstance().getLaMachine();
			
			for (String segment : segments) {
				Console.log("SEGMENT", segment);
				
				//send data
				printWriter = new PrintWriter(socket.getOutputStream());
				printWriter.println(segment);
				printWriter.println("EOT");
				printWriter.flush();
				
				//receive input
				inputStream = socket.getInputStream();
				
				//wait for input to be ready, or timeout
				TimeOut timeOut = new TimeOut(5000);
				while (!timeOut.hasTimedOut() && inputStream.available() <= 0) {}
				
				if (inputStream.available() > 0) {
					//build response
					ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
					while(inputStream.available() > 0) {
						responseBytes.write(inputStream.read());
					}
					
					//bytes > string > xml > folia
					String response = responseBytes.toString("UTF-8").replaceFirst("READY$", "");
					//Console.log(response);
					//this.source.folia = (FoLiA[]) ArrayUtils.addAll(this.source.folia, new FoLiA[] { (FoLiA) new XmlMapper().readValue(response, FoLiA.class) });
					this.source.folia = (FoLiA[]) ArrayUtils.addAll(this.source.folia, new FoLiA[] {
						(FoLiA) Singleton.getInstance().getFoliaUnmarshaller().unmarshal(new StringReader(response))
					});
				} else throw new IOException("No answer received");
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.source.folia;
	}
	
	public Pipeline summarize() {
		this._summarize();
		
		Console.log("SUMMARIZATION DONE");
		return this;
	}
	
	public void _summarize() {
		for (FoLiA folia : this.source.folia) {
			Singleton.getInstance().getRuleEngine(
				"annotation",
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"annotation.xls"
					).getFile()
				)
			).set(
				"auxMatcher", new FuzzyMatcher(
					new CSVResource(
						new File(
							this.getClass().getClassLoader().getResource(
								"aux-verbs.csv"
							).getFile()
						),
						new String[] { "id" }, "id", "id", System.getProperty("line.separator"), ","
					)
				)
			).set(
				"copMatcher", new FuzzyMatcher(
					new CSVResource(
						new File(
							this.getClass().getClassLoader().getResource(
								"cop-verbs.csv"
							).getFile()
						),
						new String[] { "id", "use"}, "id", "id", System.getProperty("line.separator"), ","
					)
				)
			).add(
				FoliaUtils.getWords(folia).toArray()
			).execute();
			
			Singleton.getInstance().getRuleEngine(
				"keyword-n",
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"keyword-extraction-n-ww.xls"
					).getFile()
				)
			).set(
				"folia", folia
			).add(
				FoliaUtils.getWords(folia).toArray()
			).execute();
			
			Singleton.getInstance().getRuleEngine(
				"keyword-adj",
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"keyword-extraction-adj.xls"
					).getFile()
				)
			).set(
				"folia", folia
			).add(
				FoliaUtils.getWords(folia).toArray()
			).execute();
			
			//new RuleEngine(
			Singleton.getInstance().getRuleEngine(
				"summarization",
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/attributes.xls"
					).getFile()
				),
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/polarity.xls"
					).getFile()
				),
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/occurrence.xls"
					).getFile()
				),
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/frequency.xls"
					).getFile()
				),
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/time-of-day.xls"
					).getFile()
				)
			).set(
				"folia", folia
			).set(
				"foliaWrapper", new FoliaWrapper(folia)
			).add(
				FoliaUtils.getWords(folia).toArray()
			).add(
				FoliaUtils.getDependencies(folia).toArray()
			).execute();
		}
	}
	
	public Pipeline annotate(String annotation) {
		this._annotate(annotation);
		
		Console.log("ANNOTATION DONE");
		return this;
	}
	
	private void _annotate(String annotation) {
		for (FoLiA folia : this.source.folia) {
			Singleton.getInstance().getRuleEngine(
				annotation,
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"annotation/".concat(annotation).concat(".xls")
					).getFile()
				)
			).set(
				"folia", folia
			).set(
				"foliaWrapper", new FoliaWrapper(folia)
			).set(
				"meddraMatcher", Singleton.getInstance().getFuzzyMatcher(
					"meddraMatcher",
					new CSVResource(
						new File(
							this.getClass().getClassLoader().getResource(
								"meddra/llt.csv"
							).getFile()
						),
						new String[] { "id", "value" }, "id", "value", System.getProperty("line.separator"), "\t"
					)
				)
			).add(
				FoliaUtils.getWords(folia).toArray()
			).add(
				FoliaUtils.getDependencies(folia).toArray()
			).execute();
		}
	}
	
}
