package jargon.model.xfolia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Dependency {

	public Head hd;
	
	public Dependant dep;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
}
