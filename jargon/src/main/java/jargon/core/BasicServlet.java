package jargon.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;

/**
 * Handles basic low-level servlet tasks, such as handling requests and sending responses.
 * All servlets inherit this class.
 * @author Michiel
 */
@SuppressWarnings("serial")
public class BasicServlet extends HttpServlet {

	protected enum OutputFormat {
		JSON, XML
	}
	
	protected String intendedAction = null;
	protected OutputFormat outputFormat = OutputFormat.JSON;
	protected HttpServletRequest servletRequest;
	protected HttpServletResponse servletResponse;
	
	public void init(ServletConfig servletConfig) throws ServletException{
		
		this.intendedAction = servletConfig.getInitParameter("intendedAction");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.servletRequest = request;
		this.servletResponse = response;
		this.servletResponse.setContentType("text/plain; charset=UTF-8");
		
		try {
			this.outputFormat = OutputFormat.valueOf(servletRequest.getHeader("Accept").split(",")[0].trim().split("/")[1].toUpperCase());
		} catch(IllegalArgumentException e) {
			this.outputFormat = OutputFormat.JSON;
		}
		
	}
	
	public Object match(String resource) {
		return this.match(resource, null);
	}
	
	/**
	 * Matches JSON string objects to a fitting strip.model class.
	 * @param file	JSON object or array to be cast.
	 * @return		File parameter represented as strip.model POJO (can be single object or array).
	 */
	public Object match(String resource, Class<?> klass) {
		try {
			Class<?>[] classes;
			
			if (klass == null) {
				classes = new Class[] {};
			} else {
				classes = new Class[] {
					klass
				};
			}
			
			for (int i=0; i<classes.length; i++) {
				try {
					return new ObjectMapper().readValue(
						resource,
						classes[i]
					);
				} catch(JsonMappingException e) {
					if ((i+1) == classes.length)
						e.printStackTrace();
				}
			}
			
			return null;
		} catch(IOException e)  {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Sends response in plain text.
	 * @param answer	String to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	public boolean reply(String answer) {
		try {
			this.servletResponse.getWriter().println(answer);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean replyAsAsked(Object answer) {
		try {
			this.getClass().getMethod(
				"replyIn".concat(this.outputFormat.toString()), Object.class
			).invoke(
				this, answer
			);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean replyAsAsked(Object[] answer) {
		try {
			this.getClass().getMethod(
				"replyIn".concat(this.outputFormat.toString()), Object[].class
			).invoke(
				this, new Object[] { answer }
			);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	/**
	 * Sends response in JSON.
	 * @param answer	Object to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	public boolean replyInJSON(Object answer) {
		try {
			return this.reply(
				new ObjectMapper().writeValueAsString(answer)
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Sends response in JSON.
	 * @param answer	Array to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	public boolean replyInJSON(Object[] answers) {
		ArrayNode arrayNode = new ObjectMapper().createArrayNode();
		
		for (Object object : answers)
			arrayNode.addPOJO(object);
		
		return this.replyInJSON(arrayNode);
	}
	
	/**
	 * Sends response in XML.
	 * @param answer	Object to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	public boolean replyInXML(Object answer) {
		try {
			return this.reply(
				new XmlMapper()
					.enable(Feature.WRITE_XML_DECLARATION)
					.writeValueAsString(answer)
				);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Sends response in XML.
	 * @param answer	Array of objects to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	public boolean replyInXML(Object[] answers) {
		return this.replyInXML(
			new Array(answers)
		);
	}
	
	private class Array {
		@JacksonXmlElementWrapper(useWrapping = false)
		public Object[] item;
		public Array(Object[] items) {
			this.item = items;
		}
	}
	
}