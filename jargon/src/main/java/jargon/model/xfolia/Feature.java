package jargon.model.xfolia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Feature {

	public Feature() {}
	
	public Feature(String subset, String klass) {
		this.subset = subset;
		this.klass = klass;
	}
	
	@JacksonXmlProperty(isAttribute = true)
	public String subset;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
}
