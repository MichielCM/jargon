package jargon.utils.fuzzymatcher;

import jargon.utils.fuzzymatcher.FuzzyMatcher.ALGORITHM;

public class FuzzyMatch {

	private String original;
	private String comparator;
	private ALGORITHM algorithm;
	private double similarity;
	
	
	public FuzzyMatch(String original, String comparator, ALGORITHM algorithm, double similarity) {
		this.original = original;
		this.comparator = comparator;
		this.algorithm = algorithm;
		this.similarity = similarity;
	}

	public String getOriginal() {
		return this.original;
	}
	
	public String getComparator() {
		return this.comparator;
	}
	
	public ALGORITHM getAlgorithm() {
		return this.algorithm;
	}
	
	public double getSimilarity() {
		return this.similarity;
	}
	
}
