package jargon.utils.csvreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import jargon.core.Console;

public class CSVReader {

	private ArrayList<CSVRecord> recordsList;
	private HashMap<String, CSVRecord> recordsMap;
	
	public CSVReader(File contents, String[] headers, String headerAsID, String rowDelimiter, String cellDelimiter) {
		try {
			this.load(new Scanner(contents), headers, headerAsID, rowDelimiter, cellDelimiter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CSVReader(String contents, String[] headers, String headerAsID, String rowDelimiter, String cellDelimiter) {
		this.load(new Scanner(contents), headers, headerAsID, rowDelimiter, cellDelimiter);
	}
	
	public CSVReader(File contents, String[] headers, String rowDelimiter, String cellDelimiter) {
		try {
			this.load(new Scanner(contents), headers, null, rowDelimiter, cellDelimiter);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public CSVReader(String contents, String[] headers, String rowDelimiter, String cellDelimiter) {
		this.load(new Scanner(contents), headers, null, rowDelimiter, cellDelimiter);
	}
	
	private void load(Scanner scanner, String[] headers, String headerAsID, String rowDelimiter, String cellDelimiter) {
		scanner.useDelimiter(rowDelimiter);
		this.recordsList = new ArrayList<CSVRecord>();
		this.recordsMap = new HashMap<String, CSVRecord>();
		
		while (scanner.hasNext()) {
			CSVRecord csvRecord = new CSVRecord(headers, scanner.next().split(cellDelimiter));
			this.recordsList.add(csvRecord);
			if (headerAsID != null) this.recordsMap.put(csvRecord.get(headerAsID), csvRecord);
		}
		
		scanner.close();
	}
	
	public CSVRecord[] read() {
		return this.recordsList.toArray(new CSVRecord[this.recordsList.size()]);
	}
	
	public CSVRecord read(String id) {
		return this.recordsMap.get(id);
	}
	
}
