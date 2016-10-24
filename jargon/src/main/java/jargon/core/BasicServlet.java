package jargon.core;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
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
	
	public void reply(String[] answer) {
		this.reply(
			StringUtils.join(answer) //.replaceAll("[<][?]xml version=\"1[.]0\" encoding=\"UTF[-]8\"[?][>]", "")
		);
	}
	
	public void reply(String answer) {
		try {
			this.servletResponse.getWriter().println(answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}