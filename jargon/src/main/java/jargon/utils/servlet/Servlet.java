package jargon.utils.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import jargon.core.Console;
import jargon.utils.upload.Uploader;
import jargon.utils.JAXBUtils;
import jargon.utils.upload.File.FileType;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {

	private HttpServletRequest servletRequest = null;
	private HttpServletResponse servletResponse = null;
	private ArrayList<MimeType> contentType = null;
	private HashMap<String,MimeType> contentTypeIndex = null;
	private ArrayList<MimeType> accept = null;
	private HashMap<String,MimeType> acceptIndex = null;
	private Uploader uploader = null;
	private String characterSet = null;
	
	public void init(ServletConfig servletConfig) throws ServletException {}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.servletRequest = request;
		this.servletResponse = response;
		
		this.contentType = new ArrayList<MimeType>();
		this.contentTypeIndex = new HashMap<String,MimeType>();
		
		if (request.getHeader("Content-Type") != null) {
			if (!request.getHeader("Content-Type").isEmpty()) {
				for (String contentType : request.getHeader("Content-Type").split(",")) {
					MimeType mimeType = new MimeType(contentType);
					this.contentType.add(mimeType);
					this.contentTypeIndex.put(mimeType.getSubType(), mimeType);
				}
			}
		}

		this.accept = new ArrayList<MimeType>();
		this.acceptIndex = new HashMap<String,MimeType>();
		
		if (request.getHeader("Accept") != null) {
			if (!request.getHeader("Accept").isEmpty()) {
				for (String accept : request.getHeader("Accept").split(",")) {
					MimeType mimeType = new MimeType(accept);
					this.accept.add(mimeType);
					this.acceptIndex.put(mimeType.getSubType(), mimeType);
				}
			}
		}
		
		this.accept.sort((o1, o2) -> o1.getQuality().compareTo((o2.getQuality())));
		
		if (request.getHeader("Accept-Charset") != null) {
			if (!request.getHeader("Accept-Charset").isEmpty()) {
				this.characterSet = request.getHeader("Accept-Charset");
				this.servletResponse.setCharacterEncoding(this.characterSet);
			}
		}
		
		this.uploader = new Uploader()
			.registerMimeTypes(FileType.TEXT, "application/json", "application/csv", "application/xml")
			.upload(request, 
				(this.contentType.size() > 0 ? this.contentType.get(0).isMultipart() : false)
			);
		
	}
	
	public Uploader parameterize() {
		return this.uploader;
	}
	
	public boolean reply(Object answer, Object... parameters) throws ServletException {
		boolean success = false;
		int index = 0;
		
		while(success == false && index < this.accept.size()) {
			success = this.reply(answer, this.accept.get(index).getSubType(), parameters);
		}
		
		return success;
	}
	
	public boolean reply(Object answer, String mimeSubType, Object... parameters) throws ServletException {
		if (this.acceptIndex.containsKey(mimeSubType)) {
			MimeType mimeType = this.acceptIndex.get(mimeSubType);
			
			ArrayList<String> answers = new ArrayList<String>();
			
			if (!answer.getClass().isArray()) {
				answer = new Object[] { answer };
			}
			
			for (int i=0; i<((Object[]) answer).length; i++) {
				Object object = ((Object[]) answer)[i];
				
				if (mimeType.isHTML()) {
					answers.add(JAXBUtils.toHTML(object, (File)parameters[0]));
				} else if (mimeType.isXML()) {
					if (i == 0)
						answers.add("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><array>");
					
					String xml = JAXBUtils.toXML(object);
					answers.add(xml.substring(xml.indexOf(System.getProperty("line.separator"))));
					
					if (i+1 == ((Object[]) answer).length)
						answers.add("</array>");
				} else if (mimeType.isJSON()) {
					answers.add(JAXBUtils.toJSON(object));
				} else if (mimeType.isText()) {
					answers.add(answer.toString());
				}
			}
			
			this.reply(
				answers.toArray(new String[answers.size()])
			);
			return true;
		} else return false;
	}
	
	private boolean reply(String answer) {
		try {
			this.servletResponse.getWriter().println(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	private boolean reply(String[] answer) throws ServletException {
		return this.reply(
			StringUtils.join(answer)
		);
	}
	
}
