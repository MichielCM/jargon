package jargon.utils.fuzzymatcher;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.comparing;

import org.apache.commons.lang.StringUtils;

import jargon.utils.JaroWinklerDistance;

public class FuzzyMatcher {

	public enum ALGORITHM {
		LEVENSHTEIN, 
		JAROWINKLER
	}
	
	private CSVResource comparators;
	
	public FuzzyMatcher() {}
	
	public FuzzyMatcher(CSVResource comparators) {
		this.comparators = comparators;
	}
	
	public FuzzyMatch matchBest(String original, ALGORITHM algorithm, double threshold) {
		FuzzyMatch[] matches = this.match(original, algorithm, threshold);
		
		if (matches.length > 0) {
			return matches[matches.length - 1];
		} else {
			return null;
		}
	}
	
	public boolean hasMatch(String original, ALGORITHM algorithm, double threshold) {
		FuzzyMatch[] matches = this.match(original, algorithm, threshold);
		return (matches.length > 0);
	}
	
	public FuzzyMatch[] match(String original, ALGORITHM algorithm, double threshold) {
		ArrayList<FuzzyMatch> matches = new ArrayList<FuzzyMatch>();
		
		for (Record comparator : this.comparators.read()) {
			double similarity = this.matches(original, comparator.getValue(), algorithm);
			if (similarity >= threshold) {
				matches.add(new FuzzyMatch(original, comparator.getValue(), algorithm, similarity));
			}
		}
		
		Collections.sort(matches, comparing(FuzzyMatch::getSimilarity));
		
		return matches.toArray(new FuzzyMatch[matches.size()]);
	}
	
	public double matches(String original, String comparator, ALGORITHM algorithm) {
		switch (algorithm) {
			case LEVENSHTEIN:
				return ((double)StringUtils.getLevenshteinDistance(original, comparator));
			case JAROWINKLER:
				return (new JaroWinklerDistance().apply(original, comparator));
			default:
				return 0.0;
		}
	}
	
	public boolean matches(String original, String comparator, ALGORITHM algorithm, double threshold) {
		switch (algorithm) {
			case LEVENSHTEIN:
				return ((double)StringUtils.getLevenshteinDistance(original, comparator) >= threshold);
			case JAROWINKLER:
				return (new JaroWinklerDistance().apply(original, comparator) >= threshold);
			default:
				return false;
		}
	}
	
}
