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

public class Pipeline {

	private Source source;
	private String text;
	private Folia[] folia;
	//private String[] tokens;
	
	public Pipeline(Source source) {
		this.source = source;
		this.text = source.text;
		//this.tokens = this.tokenize();
		Console.log(this.text);
	}
	
	public Source getSource() {
		return this.source;
	}
	
	public Folia[] getFolia() {
		return this.folia;
	}
	
	public Pipeline segmentize() {
		this.source.segments = this._segmentize();
		return this;
	}
	
	private String[] _segmentize() {
		return this.source.text.split("(?<=[.,—?!;])");
	}
	
	public Pipeline spellcheck() {
		this.text = this._spellcheck();
		return this;
	}
	
	private String _spellcheck() {
		
		/*try {
			JLanguageTool jLanguageTool = new JLanguageTool(new Dutch());
			List<RuleMatch> matches = jLanguageTool.check("dit is een teksst met fauten.");
			
			for (RuleMatch match : matches) {
			  System.out.println("Potential error at characters " +
			      match.getFromPos() + "-" + match.getToPos() + ": " +
			      match.getMessage());
			  System.out.println("Suggested correction(s): " +
			      match.getSuggestedReplacements());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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
			
			for (String segment : (segments ? this.source.segments : new String[] { this.text })) {
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
