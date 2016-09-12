package jargon.utils.fuzzymatcher;

public abstract class Resource {

	public abstract Record[] read();
	
	public abstract Record read(String id);
	
}
