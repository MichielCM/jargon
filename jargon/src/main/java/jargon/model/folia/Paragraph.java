package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Paragraph {
	@JacksonXmlElementWrapper(useWrapping = false)
	public String[] t;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	public Section[] s;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
}
