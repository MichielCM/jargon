package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Entity {

	@JacksonXmlElementWrapper(useWrapping = false)
	public WordReference[] wref;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String set;
	
}
