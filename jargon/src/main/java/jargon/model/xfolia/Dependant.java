package jargon.model.xfolia;

import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Dependant {

	public WordReference wref;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotator;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotatortype;
	
	@JacksonXmlProperty(isAttribute = true)
	public String n;
	
	@JacksonXmlProperty(isAttribute = true)
	public Date datetime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String auth;
	
}
