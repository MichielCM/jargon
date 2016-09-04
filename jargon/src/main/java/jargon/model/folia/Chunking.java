package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Chunking {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Chunk[] chunk;
	
	@JacksonXmlProperty(isAttribute = true)
	public String id;
	
}
