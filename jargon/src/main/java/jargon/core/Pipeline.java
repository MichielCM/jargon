package jargon.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang.ArrayUtils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jargon.core.Console;
import jargon.core.Settings;
import jargon.model.Source;
import jargon.model.folia.Folia;
import jargon.utils.TimeOut;
import jargon.utils.autocorrector.AutoCorrector;

public class Pipeline {

	public enum Segments {
		RAW, SENTENCES, TOKENS
	}
	
	private Source source;
	private String text;
	private Folia[] folia;
	//private String[] tokens;
	
	public Pipeline(Source source) {
		this.source = source;
		//this.text = source.raw;
		//this.tokens = this.tokenize();
		//Console.log(this.text);
	}
	
	public Source getSource() {
		return this.source;
	}
	
	public Folia[] getFolia() {
		return this.folia;
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
				return this.source.raw[0].split("(?<=[.,—?!;])");
			case TOKENS:
				return null; //TODO
			default:
				return null;
		}
		
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
				segments[i] = autoCorrector.autoCorrect(segments[i], "UNABBREVIATION");
				
				/*CSVReader csvReader = new CSVReader(
					new File(this.getClass().getClassLoader().getResource("custom-abbreviations.csv").getFile()),
					new String[] {"abbreviation","description"},
					"abbreviation",
					"\r\n",
					";"
				);*/
				
				/*for (CSVRecord csvRecord : csvReader.read()) {
					autoCorrector.addCustomRules(
						new AbbreviationReplaceRule(
							csvRecord.get("abbreviation"), csvRecord.get("description"), true
						),
						new AbbreviationReplaceRule(
							csvRecord.get("abbreviation"), csvRecord.get("description"), false
						)
					);
				}*/
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
	
	public Pipeline frog(boolean segments) {
		this._frog(segments);
		return this;
	}
	
	@SuppressWarnings("resource")
	private Folia[] _frog(boolean segments) {
		Socket socket = null;
		PrintWriter printWriter = null;
		InputStream inputStream = null;
		
		try {
			Settings laMachine = new Settings("lamachine");
			
			socket = new Socket(
				laMachine.getAsString("host"),
				laMachine.getAsInt("port")
			);
			socket.setKeepAlive(false);
			
			//Socket socket = SingletonFactory.getSingletonFactory().getLaMachine();
			
			//for (String segment : (segments ? this.source.sentences : new String[] { this.text })) {
			for (String segment : (new String[] { this.text })) {
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
					this.folia = (Folia[]) ArrayUtils.addAll(this.folia, new Folia[] { (Folia) new XmlMapper().readValue(response, Folia.class) });
				} else throw new IOException("No answer received");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				printWriter.close();
				inputStream.reset();
				inputStream.close();
            	socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return this.folia;
	}
	
	/*public String[] tokenize() {
		return this.source.text.split("\\s+");
	}*/
	
	public void anonymize() {}
	
	public void unabbreviate() {}
	
}
