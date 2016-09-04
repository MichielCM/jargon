package jargon.utils.fuzzymatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CSVResource extends Resource {

	private ArrayList<Record> recordsList;
	private HashMap<String, Record> recordsMap;
	
	@SuppressWarnings("resource")
	public CSVResource(File file, String rowDelimiter, String cellDelimiter) {
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(file).useDelimiter(rowDelimiter);
			this.recordsList = new ArrayList<Record>();
			this.recordsMap = new HashMap<String, Record>();
			
			while (fileScanner.hasNext()) {
				String row = fileScanner.next();
				Record record = new Record(
					row.substring(0, row.indexOf(cellDelimiter)),
					this.trim(row.substring(row.indexOf(cellDelimiter) + 1), "\"")
				);
				
				this.recordsList.add(record);
				this.recordsMap.put(record.getId(), record);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			fileScanner.close();
		}
	}
	
	@Override
	public Record[] read() {
		return this.recordsList.toArray(new Record[this.recordsList.size()]);
	}
	
	@Override
	public Record read(String id) {
		return this.recordsMap.get(id);
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
