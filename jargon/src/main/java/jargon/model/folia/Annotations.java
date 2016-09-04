package jargon.model.folia;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Annotations {

	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "token-annotation")
	public Annotation[] tokenAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "pos-annotation")
	public Annotation[] posAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "lemma-annotation")
	public Annotation[] lemmaAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "morphological-annotation")
	public Annotation[] morphologicalAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "chunking-annotation")
	public Annotation[] chunkingAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "entity-annotation")
	public Annotation[] entityAnnotation;
	
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "dependency-annotation")
	public Annotation[] dependencyAnnotation;
	
}
