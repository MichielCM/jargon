package jargon.model.folia;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Chunk {

	@JacksonXmlElementWrapper(useWrapping = false)
	public WordReference[] wref;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
	@JacksonXmlProperty(isAttribute = true)
	public BigDecimal confidence;
	
}
