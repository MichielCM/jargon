package jargon.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.lang.ArrayUtils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jargon.core.Console;
import jargon.model.Source;
import jargon.model.folia.Folia;
import jargon.utils.TimeOut;
import jargon.utils.autocorrector.AutoCorrector;

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
				return this.source.full[0].split("(?<=[.,—?!;])");
			case TOKENS:
				return this.source.full[0].split("\b");
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
	
	private Folia[] _frog(Segments segmentType) {
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
					Console.log(response);
					this.source.folia = (Folia[]) ArrayUtils.addAll(this.source.folia, new Folia[] { (Folia) new XmlMapper().readValue(response, Folia.class) });
				} else throw new IOException("No answer received");
			}
		} catch (IOException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return this.source.folia;
	}
	
}
