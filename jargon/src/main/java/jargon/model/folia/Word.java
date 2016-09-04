package jargon.model.folia;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Word {

	public PoS pos;
	
	public Morphology morphology;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
	@JacksonXmlProperty(isAttribute = true)
	public String space;
	
	public Lemma lemma;
	
	public String t;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	public Sense[] sense;
	
	@JsonIgnore
	public Dependency head;
	
	@JsonIgnore
	public Dependency[] dependants;
	
}
