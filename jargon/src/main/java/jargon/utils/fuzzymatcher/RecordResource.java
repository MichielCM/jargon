package jargon.utils.fuzzymatcher;

import jargon.core.Console;

public class RecordResource extends Resource {

	public RecordResource(Record[] records) {
		for (Record record : records) {
			Console.log(record.getId(), record.getValue());
			this.recordsList.add(record);
			this.recordsMap.put(record.getId(), record);
		}
	}
	
	@Override
	public Record[] read() {
		return super.recordsList.toArray(new Record[super.recordsList.size()]);
	}
	
	@Override
	public Record read(String id) {
		return super.recordsMap.get(id);
	}
	
}
