package jargon.utils.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jargon.core.Console;
import jargon.utils.upload.Uploader;
import jargon.utils.upload.File.FileType;

@SuppressWarnings("serial")
public class Servlet extends HttpServlet {

	private HttpServletRequest servletRequest = null;
	private HttpServletResponse servletResponse = null;
	private ArrayList<MimeType> contentType = new ArrayList<MimeType>();
	private HashMap<String,MimeType> contentTypeIndex = new HashMap<String,MimeType>();
	private ArrayList<MimeType> accept = new ArrayList<MimeType>();
	private HashMap<String,MimeType> acceptIndex = new HashMap<String,MimeType>();
	private Uploader uploader = null;
	private String characterSet = null;
	
	public void init(ServletConfig servletConfig) throws ServletException {}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.servletRequest = request;
		this.servletResponse = response;
		
		if (request.getHeader("Content-Type") != null) {
			if (!request.getHeader("Content-Type").isEmpty()) {
				for (String contentType : request.getHeader("Content-Type").split(",")) {
					MimeType mimeType = new MimeType(contentType);
					this.contentType.add(mimeType);
					this.contentTypeIndex.put(mimeType.getSubType(), mimeType);
				}
			}
		}
		
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
	
	public void reply(Object answer) throws ServletException {
		this.reply(answer, this.accept.get(0).getSubType());
	}
	
	public void reply(Object answer, String mimeSubType) throws ServletException {
		try {
			if (this.acceptIndex.containsKey(mimeSubType)) {
				if (answer.getClass().isArray()) {
					
				} else if (answer.getClass().isPrimitive()) {
					
				} else {
					
				}
			} else throw new ServletException("mimeSubType not accepted: ".concat(mimeSubType));
		} catch(ServletException e) {
			e.printStackTrace();
		}
	}
	
}
