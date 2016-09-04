package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Section {
	
	@JacksonXmlElementWrapper(useWrapping = false)
	public Word[] w;
	
	public Chunking chunking;
	
	public Dependencies dependencies;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
	public String t;
	
}
