package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Entities {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Entity[] entity;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
}
