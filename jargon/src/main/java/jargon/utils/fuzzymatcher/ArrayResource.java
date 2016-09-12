package jargon.utils.fuzzymatcher;

import java.util.ArrayList;

public class ArrayResource extends Resource {

	private ArrayList<Record> recordsList;
	
	public ArrayResource(String[] strings) {
		this.recordsList = new ArrayList<Record>();
		
		for (int i=0; i<strings.length; i++) {
			this.recordsList.add(new Record("", strings[i]));
		}
	}

	@Override
	public Record[] read() {
		return this.recordsList.toArray(new Record[this.recordsList.size()]);
	}

	@Override
	public Record read(String id) {
		//HashMap not available for string array without IDs");
		return null;
	}
	
}
