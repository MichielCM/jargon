package jargon.core;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
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
		
		return this;
	}
	
	private String[] _segmentize(Segments segmentType) {
		switch(segmentType) {
			case SENTENCES:
				return this.source.full[0].split("(?<=[.,—?!;\\n])");
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
		
		return this;
	}
	
	private String[] _clean(Segments segmentType) {
		try {
			ArrayList<String> segments = new ArrayList<String>();
			
			for (String segment : (String[]) this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source)) {
				segment = segment.trim();
				
				if (segment.length() > 0) {
					if (segment.substring(0,1).equals(segment.substring(0,1).toUpperCase()))
						segment = segment.substring(0,1).toLowerCase().concat(segment.substring(1));
					if (segment.substring(segment.length() - 1).matches("[.,—?!;]"))
						segment = segment.substring(0, segment.length() - 1);
				}
				
				segments.add(segment);
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
		
		return this;
	}
	
	private FoLiA[] _frog(Segments segmentType) {
		PrintWriter printWriter = null;
		InputStream inputStream = null;
		
		try {
			String[] segments = (String[])this.source.getClass().getDeclaredField(segmentType.toString().toLowerCase()).get(this.source);
			Socket socket = SingletonFactory.getSingletonFactory().getLaMachine();
			
			for (String segment : segments) {
				Console.log(segment);
				
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
						(FoLiA) JAXBContext.newInstance(FoLiA.class).createUnmarshaller().unmarshal(new StringReader(response))
					});
				} else throw new IOException("No answer received");
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException | JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.source.folia;
	}
	
	public Pipeline annotate() {
		this._annotate();
		return this;
	}
	
	public void _annotate() {
		try {
			for (FoLiA folia : this.source.folia) {
				new RuleEngine(
					ResourceType.XLS,
					IOUtils.toByteArray(
						this.getClass().getClassLoader().getResourceAsStream(
							"annotation.xls"
						)
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
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Pipeline summarize() {
		this._summarize();
		return this;
	}
	
	public void _summarize() {
		for (FoLiA folia : this.source.folia) {
			new RuleEngine(
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
			
			new RuleEngine(
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
			
			new RuleEngine(
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
			/*).set(
				"occurrenceMatcher", new FuzzyMatcher(
					new CSVResource(
						new File(
							this.getClass().getClassLoader().getResource(
								"occurrences.csv"
							).getFile()
						),
						new String[] { "id" }, "id", "id", System.getProperty("line.separator"), "&"
					)
				)
			).set(
				"timeMatcher", new FuzzyMatcher(
					new CSVResource(
						new File(
							this.getClass().getClassLoader().getResource(
								"time.csv"
							).getFile()
						),
						new String[] { "id", "value" }, "id", "id", System.getProperty("line.separator"), "&"
					)
				)*/
			).add(
				FoliaUtils.getWords(folia).toArray()
			).add(
				FoliaUtils.getDependencies(folia).toArray()
			).execute();
			
			new RuleEngine(
				ResourceType.XLS,
				new File(
					this.getClass().getClassLoader().getResource(
						"summarization/family.xls"
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
	
}
