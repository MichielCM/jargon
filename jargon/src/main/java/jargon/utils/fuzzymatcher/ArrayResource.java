package jargon.utils.fuzzymatcher;

import java.util.ArrayList;

public class ArrayResource extends Resource {

	//private ArrayList<Record> recordsList;
	
	public ArrayResource(String[] strings) {
		super.recordsList = new ArrayList<Record>();
		
		for (int i=0; i<strings.length; i++) {
			super.recordsList.add(new Record("", strings[i]));
		}
	}

	@Override
	public Record[] read() {
		return super.recordsList.toArray(new Record[super.recordsList.size()]);
	}

	@Override
	public Record read(String id) {
		//HashMap not available for string array without IDs");
		return null;
	}
	
}
