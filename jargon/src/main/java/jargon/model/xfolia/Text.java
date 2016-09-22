package jargon.model.xfolia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Text {
	@JacksonXmlElementWrapper(useWrapping = false)
	public Paragraph[] p;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
}
