package jargon.model.xfolia;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Morphology {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Morpheme[] morpheme;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotator;
	
	@JacksonXmlProperty(isAttribute = true)
	public String annotatortype;
	
	@JacksonXmlProperty(isAttribute = true)
	public BigDecimal confidence;
	
	@JacksonXmlProperty(isAttribute = true)
	public String n;
	
	@JacksonXmlProperty(isAttribute = true)
	public Date datetime;
	
	@JacksonXmlProperty(isAttribute = true)
	public String auth;
	
	@JacksonXmlProperty(isAttribute = true)
	public String set;
	
}
