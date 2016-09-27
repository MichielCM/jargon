package jargon.utils.fuzzymatcher;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Resource {

	protected ArrayList<Record> recordsList = new ArrayList<Record>();
	protected HashMap<String, Record> recordsMap = new HashMap<String, Record>();
	
	public abstract Record[] read();
	
	public abstract Record read(String id);
	
}
