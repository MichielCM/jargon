package jargon.core;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

import org.apache.commons.lang3.StringEscapeUtils;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator.Feature;

import jargon.model.folia.FoLiA;

/**
 * Handles basic low-level servlet tasks, such as handling requests and sending responses.
 * All servlets inherit this class.
 * @author Michiel
 */
@SuppressWarnings("serial")
public class BasicServlet extends HttpServlet {
	
	protected String mimeType = null;
	protected HttpServletRequest servletRequest;
	protected HttpServletResponse servletResponse;
	
	public void init(ServletConfig servletConfig) throws ServletException{
		
		System.setProperty("javax.xml.bind.context.factory","org.eclipse.persistence.jaxb.JAXBContextFactory");
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.doPost(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.servletRequest = request;
		this.servletResponse = response;
		this.servletResponse.setContentType("text/plain; charset=UTF-8");
		
		try {
			this.mimeType = servletRequest.getHeader("Accept").split(",")[0].trim().replaceFirst("^text/", "application/");
			
			switch (this.mimeType) {
				case "text/xml":
					this.mimeType = "application/xml";
					break;
				case "text/json":
					this.mimeType = "application/json";
					break;
			}
		} catch(IllegalArgumentException e) {}
		
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
	
	/*@XmlAccessorType(XmlAccessType.FIELD)
	@XmlRootElement(name = "wrapper")
	private class PrimitiveWrapper {
		@XmlAttribute(name = "item")
	    @XmlSchemaType(name = "anySimpleType")
		public Object item;
		public PrimitiveWrapper(Object item) {
			this.item = item;
		}
	}
	
	public void reply(Object[] answer, String mimeType) {
		ArrayList<PrimitiveWrapper> wrapper = new ArrayList<PrimitiveWrapper>();
		Console.log(answer.getClass());
		Console.log(answer.getClass().getComponentType());
		Console.log(answer.getClass().getComponentType().getName());
		for (Object object : answer) {
			wrapper.add(new PrimitiveWrapper(object));
		}
		
		this.reply(wrapper, mimeType);
	}*/
	
	public void reply(Object answer, String mimeType) {
		try {
			StringWriter stringWriter = new StringWriter();
			Marshaller marshaller = JAXBContext.newInstance(answer.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, mimeType);
			marshaller.marshal(answer, stringWriter);
			this.reply(stringWriter.toString());
		} catch(MarshalException me) {
			//Generate array of 'primitive' types in JSON if required
			if (answer.getClass().isArray() && mimeType.equalsIgnoreCase("application/json")) {
				this.reply(
					"[\"".concat(
						String.join("\",\"", Arrays.asList(
							Arrays.toString(
								(Object[])answer
							).split("[\\[\\]]")[1].split(", ")
						).stream().map(
							(str) -> StringEscapeUtils.escapeJson(str)
						).collect(
							Collectors.toList()
						))
					).concat("\"]")
				);
			}
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reply(Object answer) throws IOException {
		if (this.mimeType != null)
			this.reply(answer, this.mimeType);
		else
			throw new IOException("No desired output format specified through mimetype.");
	}
	
	public void reply(String answer) {
		try {
			this.servletResponse.getWriter().println(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Sends response in plain text.
	 * @param answer	String to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	/*public boolean reply(String answer) {
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
	}*/
	
	/**
	 * Sends response in JSON.
	 * @param answer	Object to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	/*public boolean replyInJSON(Object answer) {
		try {
			return this.reply(
				new ObjectMapper().writeValueAsString(answer)
			);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}*/
	
	/**
	 * Sends response in JSON.
	 * @param answer	Array to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	/*public boolean replyInJSON(Object[] answers) {
		ArrayNode arrayNode = new ObjectMapper().createArrayNode();
		
		for (Object object : answers)
			arrayNode.addPOJO(object);
		
		return this.replyInJSON(arrayNode);
	}*/
	
	/**
	 * Sends response in XML.
	 * @param answer	Object to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	/*public boolean replyInXML(Object answer) {
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
	}*/
	
	/**
	 * Sends response in XML.
	 * @param answer	Array of objects to be sent as answer.
	 * @return			Whether or not reply was successfully sent.
	 */
	/*public boolean replyInXML(Object[] answers) {
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
	}*/
	
	public String toXML(Object object) {
		try {
			Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, "application/xml");
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public String toJSON(Object object) {
		try {
			Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(JAXBContextProperties.MEDIA_TYPE, "application/json");
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}