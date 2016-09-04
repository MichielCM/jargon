package jargon.model.folia;

import java.math.BigDecimal;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PoS {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Feature[] feat;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
	@JacksonXmlProperty(isAttribute = true)
	public BigDecimal confidence;
	
	@JacksonXmlProperty(isAttribute = true)
	public String head;
	
}
