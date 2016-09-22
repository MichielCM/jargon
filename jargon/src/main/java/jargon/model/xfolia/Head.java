package jargon.model.xfolia;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Head {

	public WordReference wref;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String set;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
	@JacksonXmlProperty(isAttribute = true)
	public BigDecimal confidence;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotator;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotatortype;
	
	@JacksonXmlProperty(isAttribute = true)
	public String n;
	
	@JacksonXmlProperty(isAttribute = true)
	public Date datetime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String begintime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String endtime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String src;
	
	@JacksonXmlProperty(isAttribute = true)
	public String speaker;
	
	@JacksonXmlProperty(isAttribute = true)
	public String auth;
	
}
