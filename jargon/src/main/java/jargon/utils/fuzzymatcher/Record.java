package jargon.utils.fuzzymatcher;

public class Record {

	private String id;
	private String value;
	
	public Record() {}
	
	public Record(String id, String value) {
		this.id = id;
		this.value = value;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
}
