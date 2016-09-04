package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Lemma {

	@JacksonXmlProperty(isAttribute = true, localName = "class")
	public String klass;
	
}
