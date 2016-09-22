package jargon.model;

import jargon.model.folia.FoLiA;

public class Source {

	public String input;
	public String[] full;
	public String[] sentences;
	public String[] tokens;
	public FoLiA[] folia;
	
	public Source(String text) {
		this.input = text;
		this.full = new String[] { text };
	}
	
}
