package jargon.model.folia;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement
public class Folia {
	public Metadata metadata;
	
	public Text text;
	
	public String id;
	public String generator;
	public String version;
	
	@JsonIgnore
	public void prepareForAnalysis() {
		for (Paragraph p : this.text.p) {
			for (Section s : p.s) {
				for (Word w : s.w) {
					ArrayList<Dependency> dependencyArray = new ArrayList<Dependency>();
					for (Dependency dependency : s.dependencies.dependency) {
						if (dependency.dep.wref.id.equals(w.id)) {
							w.head = dependency;
						} else if (dependency.hd.wref.id.equals(w.id)) {
							dependencyArray.add(dependency);
						}
					}
					w.dependants = dependencyArray.toArray(new Dependency[dependencyArray.size()]);
				}
			}
		}
	}
	
	@JsonIgnore
	public Word[] getWords() {
		ArrayList<Word> words = new ArrayList<Word>();
		
		for (Paragraph p : this.text.p) {
			for (Section s : p.s) {
				for (Word w : s.w) {
					words.add(w);
				}
			}
		}
		
		return words.toArray(new Word[words.size()]);
	}
	
	@JsonIgnore
	public Dependency[] getDependencies() {
		ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
		
		for (Paragraph p : this.text.p) {
			for (Section s : p.s) {
				for (Dependency d : s.dependencies.dependency) {
					dependencies.add(d);
				}
			}
		}
		
		return dependencies.toArray(new Dependency[dependencies.size()]);
	}
}
