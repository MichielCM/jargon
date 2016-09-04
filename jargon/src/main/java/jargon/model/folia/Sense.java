package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Sense {

	public Sense() {}
	
	public Sense(String synset, String klass) {
		this.synset = synset;
		this.klass = klass;
	}
	
	@JacksonXmlElementWrapper(useWrapping = false)
	public Feature[] feat;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
	@JacksonXmlProperty(isAttribute = true)
	public String synset;
	
}
