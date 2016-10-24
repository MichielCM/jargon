package jargon.utils;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

public final class JAXBUtils {

	public static java.util.List<?> getElementsByType(java.util.List<?> input, Class<?> type) {
		return input
			.stream()
			.filter(
				obj -> obj.getClass().equals(type)
			).collect(
				Collectors.toList()
			);
	}
	
	public static String toXML(Object object) {
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
	
	public static String toXML(Object object, boolean ignoreXMLNS) {
		if (!ignoreXMLNS)
			return toXML(object);
		else
			return toXML(object).replaceAll("xmlns(=|:).*?(?=\\s|>)", "");
	}
	
	public static String toHTML(Object object, File xsltResource) {
		StringWriter stringWriter = new StringWriter();
		
		try {
			TransformerFactory.newInstance().newTransformer(
				new StreamSource(
					xsltResource
					/*new StringReader(
						//"<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"/\">	<head>		<link rel=\"stylesheet\" type=\"text/css\" href=\"rules.css\"/>	</head>	<body>		<xsl:for-each select=\"//w\">			<p><xsl:value-of select=\"t\"/></p>		</xsl:for-each>	</body></xsl:template>	</xsl:stylesheet>"
							"<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\"><xsl:template match=\"/\"><xsl:for-each select=\"//w\">			<p><xsl:value-of select=\"t\"/></p>		</xsl:for-each></xsl:template>	</xsl:stylesheet>"
					)*/
		    	)
			).transform(
				new StreamSource(
		    		new StringReader(
		    			toXML(object, true)
		    		)
		    	), new StreamResult(
		    		stringWriter
		    	)
			);
		} catch(TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stringWriter.toString().replaceAll("[<][?]xml version=\"1[.]0\" encoding=\"UTF[-]8\"[?][>]", "");
	}
	
	public static String toJSON(Object object) {
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
