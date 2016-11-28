package jargon.utils.fuzzymatcher;

import java.util.ArrayList;

public class ArrayResource extends Resource {

	public ArrayResource(String[] values) {
		this(null, values);
	}
	
	public ArrayResource(String[] ids, String[] values) {
		super.recordsList = new ArrayList<Record>();
		
		for (int i=0; i<values.length; i++) {
			super.recordsList.add(new Record((ids == null ? "" : ids[i]), values[i]));
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
