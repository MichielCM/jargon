package jargon.utils.fuzzymatcher;

import java.io.File;

import jargon.utils.csvreader.CSVReader;
import jargon.utils.csvreader.CSVRecord;

public class CSVResource extends Resource {
	
	public CSVResource(File file, String[] headers, String id, String value, String rowDelimiter, String cellDelimiter) {
		CSVReader csvReader = new CSVReader(file, headers, id, rowDelimiter, cellDelimiter);
		
		for (CSVRecord csvRecord : csvReader.read()) {
			Record record = new Record(
				csvRecord.get(id),
				csvRecord.get(value)
			);
			
			super.recordsList.add(record);
			super.recordsMap.put(record.getId(), record);
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
