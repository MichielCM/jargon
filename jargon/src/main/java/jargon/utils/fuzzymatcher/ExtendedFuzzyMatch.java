package jargon.utils.fuzzymatcher;

//import java.util.Comparator;

public class ExtendedFuzzyMatch /*implements Comparator*/ {

	private String original;
	private String comparator;
	private double jaroWinkler;
	private double cosineWords;
	private double cosineChars;
	private Record record;
	
	public ExtendedFuzzyMatch(String original, String comparator, double jaroWinkler, double cosineWords, double cosineChars, Record record) {
		this.original = original;
		this.comparator = comparator;
		this.jaroWinkler = jaroWinkler;
		this.cosineWords = cosineWords;
		this.cosineChars = cosineChars;
		this.record = record;
	}

	public String getOriginal() {
		return this.original;
	}
	
	public String getComparator() {
		return this.comparator;
	}
	
	public double getJaroWinkler() {
		return this.jaroWinkler;
	}
	
	public double getCosineWords() {
		return this.cosineWords;
	}
	
	public double getCosineChars() {
		return this.cosineChars;
	}
	
	public Record getRecord() {
		return this.record;
	}

	/*@Override
	public int compare(Object o1, Object o2) {
		if (((ExtendedFuzzyMatch) o1).getJaroWinkler() > ((ExtendedFuzzyMatch) o2).getJaroWinkler())
			return 1;
		else
			return 0;
	}*/
	
}
