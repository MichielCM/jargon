package jargon.model.xfolia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Alt {

	public Lemma lemma;
	
	public Morphology morphology;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	@JacksonXmlProperty(isAttribute = true)
	public String auth;
	
}
