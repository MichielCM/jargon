package jargon.utils.fuzzymatcher;

import java.util.ArrayList;
import java.util.Collections;
import static java.util.Comparator.comparing;

import org.apache.commons.lang3.StringUtils;

import jargon.core.Console;
import jargon.utils.CosineSimilarity;

public class FuzzyMatcher {

	public enum ALGORITHM {
		BINARY,
		LEVENSHTEIN, 
		JAROWINKLER,
		COSINE,
		REGEX
	}
	
	private Resource comparators;
	
	public FuzzyMatcher() {}
	
	public FuzzyMatcher(Resource comparators) {
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
	
	public boolean hasMatch(String original, double threshold, ALGORITHM... algorithms) {
		Console.log(original);
		
		for (int i=0; i<algorithms.length; i++) {
			if (this.hasMatch(original, algorithms[i], threshold))
				return true;
		}
		
		return false;
	}
	
	public boolean hasMatch(String original, ALGORITHM[] algorithms, Double[] thresholds) {
		for (int i=0; i<algorithms.length; i++) {
			if (this.hasMatch(original, algorithms[i], thresholds[i]))
				return true;
		}
		
		return false;
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
				matches.add(new FuzzyMatch(original, comparator.getValue(), algorithm, similarity, comparator));
				Console.log(matches.get(matches.size() - 1).getComparator(), matches.get(matches.size() - 1).getSimilarity(), matches.get(matches.size() - 1).getOriginal());
			}
		}
		
		Collections.sort(matches, comparing(FuzzyMatch::getSimilarity));
		
		return matches.toArray(new FuzzyMatch[matches.size()]);
	}
	
	public double matches(String original, String comparator, ALGORITHM algorithm) {
		switch (algorithm) {
			case LEVENSHTEIN:
				return StringUtils.getLevenshteinDistance(original, comparator);
			case JAROWINKLER:
				return StringUtils.getJaroWinklerDistance(original, comparator);
			case COSINE:
				return CosineSimilarity.calculate(original, comparator);
			case REGEX:
				return (original.matches(comparator) ? 1.0 : 0.0);
			default:
				return (original.equals(comparator) ? 1.0 : 0.0);
		}
	}
	
	public boolean matches(String original, String comparator, ALGORITHM algorithm, double threshold) {
		switch (algorithm) {
			case LEVENSHTEIN:
				return (StringUtils.getLevenshteinDistance(original, comparator) >= threshold);
			case JAROWINKLER:
				return (StringUtils.getJaroWinklerDistance(original, comparator) >= threshold);
			case COSINE:
				return (CosineSimilarity.calculate(original, comparator) >= threshold);
			case REGEX:
				return original.matches(comparator);
			default:
				return original.equals(comparator);
		}
	}
	
}
