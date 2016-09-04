package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WordReference {

	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String t;
	
}
