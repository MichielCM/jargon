package jargon.model.xfolia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Dependencies {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Dependency[] dependency;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
}
