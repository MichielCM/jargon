package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Morphology {

	@JacksonXmlElementWrapper(useWrapping = false)
	public Morpheme[] morpheme;
	
}
