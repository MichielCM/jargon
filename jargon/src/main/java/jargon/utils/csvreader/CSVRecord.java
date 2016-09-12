package jargon.utils.csvreader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CSVRecord {

	private HashMap<String, String> valuesMap;
	
	public CSVRecord(String[] headers, String[] values) {
		this.valuesMap = new HashMap<String, String>();
		
		for (int i=0; i<values.length; i++) {
			this.valuesMap.put(headers[i], this.trim(values[i], "\""));
		}
	}
	
	public String get(String id) {
		return this.valuesMap.get(id);
	}
	
	private String trim(String value, String... trims) {
		String trimmedValue = value;
		List<String> trimsList = Arrays.asList(trims);
		
		while (trimsList.contains(trimmedValue.substring(0,1))) {
			trimmedValue = trimmedValue.substring(1);
		}
		
		while (trimsList.contains(trimmedValue.substring(trimmedValue.length() - 1))) {
			trimmedValue = trimmedValue.substring(0, trimmedValue.length() - 1);
		}
		
		return trimmedValue;
	}
	
}
