package jargon.model;

public class Source {

	public String[] raw;
	public String[] sentences;
	public String[] tokens;
	
	public Source(String text) {
		this.raw = new String[] { text };
	}
	
}
