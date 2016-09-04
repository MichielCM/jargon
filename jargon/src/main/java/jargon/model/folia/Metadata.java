package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Metadata {

	public Annotations annotations;
	
	@JacksonXmlProperty(isAttribute = true)
	public String type;
	
}
