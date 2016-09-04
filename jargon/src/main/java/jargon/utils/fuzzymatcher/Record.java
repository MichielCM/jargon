package jargon.utils.fuzzymatcher;

public class Record {

	private String id;
	private String value;
	
	public Record(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
