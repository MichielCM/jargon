package jargon.model;

import jargon.model.folia.Folia;

public class Source {

	public String input;
	public String[] full;
	public String[] sentences;
	public String[] tokens;
	public Folia[] folia;
	
	public Source(String text) {
		this.input = text;
		this.full = new String[] { text };
	}
	
}
