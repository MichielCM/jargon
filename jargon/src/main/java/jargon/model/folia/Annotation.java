package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Annotation {

	@JacksonXmlProperty(isAttribute = true)
	public String annotator;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotatortype;
	
	@JacksonXmlProperty(isAttribute = true)
	public String datetime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String set;
	
}
