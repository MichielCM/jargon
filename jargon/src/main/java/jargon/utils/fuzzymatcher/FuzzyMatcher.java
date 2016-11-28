package jargon.utils.fuzzymatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.util.Comparator.comparing;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import jargon.core.Console;
import jargon.core.Singleton;
import jargon.utils.CosineSimilarity;

public class FuzzyMatcher {

	public enum ALGORITHM {
		BINARY,
		LEVENSHTEIN, 
		JAROWINKLER,
		COSINEWORDS,
		COSINECHARS,
		REGEX
	}
	
	private Resource comparators;
	
	public FuzzyMatcher() {}
	
	public FuzzyMatcher(Resource comparators) {
		this.comparators = comparators;
	}
	
	public FuzzyMatch matchBest(String original, ALGORITHM algorithm, double threshold) {
		return this.matchBest(original, algorithm, threshold, false);
	}
	
	public FuzzyMatch matchBest(String original, ALGORITHM algorithm, double threshold, boolean matchOnId) {
		FuzzyMatch[] matches = this.match(original, algorithm, threshold, matchOnId);
		
		if (matches.length > 0) {
			return matches[matches.length - 1];
		} else {
			return null;
		}
	}
	
	public FuzzyMatch matchBest(ALGORITHM algorithm, double threshold) {
		return this.matchBest(null, algorithm, threshold, false);
	}
	
	public boolean hasMatch(String original, double threshold, ALGORITHM... algorithms) {
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
	
	public boolean hasMatch(String original, ALGORITHM algorithm, double threshold, boolean matchOnId) {
		FuzzyMatch[] matches = this.match(original, algorithm, threshold, matchOnId);
		return (matches.length > 0);
	}
	
	public boolean hasMatch(ALGORITHM algorithm, double threshold) {
		FuzzyMatch[] matches = this.match(null, algorithm, threshold, true);
		return (matches.length > 0);
	}
	
	public boolean hasMatch(String original, ALGORITHM algorithm, double threshold) {
		return this.hasMatch(original, algorithm, threshold, false);
	}
	
	public FuzzyMatch[] match(String original, ALGORITHM algorithm, double threshold, boolean matchOnId) {
		ArrayList<FuzzyMatch> matches = new ArrayList<FuzzyMatch>();
		
		for (Record comparator : this.comparators.read()) {
			double similarity = this.matches((original == null ? (matchOnId ? comparator.getValue() : comparator.getId()) : original), (matchOnId ? comparator.getId() : comparator.getValue()), algorithm);
			if (similarity >= threshold) {
				matches.add(new FuzzyMatch((original == null ? (matchOnId ? comparator.getValue() : comparator.getId()) : original), (matchOnId ? comparator.getId() : comparator.getValue()), algorithm, similarity, comparator));
				Console.log(matches.get(matches.size() - 1).getComparator(), matches.get(matches.size() - 1).getSimilarity(), matches.get(matches.size() - 1).getOriginal());
			}
		}
		
		Collections.sort(matches, comparing(FuzzyMatch::getSimilarity));
		
		return matches.toArray(new FuzzyMatch[matches.size()]);
	}
	
	public FuzzyMatch[] match(String original, ALGORITHM algorithm, double threshold) {
		return this.match(original, algorithm, threshold, false);
	}
	
	public /*FuzzyMatch[]*/ boolean matchLongestCommonSubstring(String original, String baseToken, double threshold) {
		Console.log("matchLongestCommonSubstring");
		Console.log(original);
		Console.log(baseToken);
		
		ArrayList<ExtendedFuzzyMatch> matches = new ArrayList<ExtendedFuzzyMatch>();
		
		Record[] records = (Record[]) Singleton.getInstance().getSQLManager().queryPrepared(
			"SELECT longest_common_substring(?,`description`) AS id, description AS value FROM llt WHERE `description` REGEXP ?;",
			new String[] { original, baseToken },
			"jargon.utils.fuzzymatcher.Record"
		);
		
		Console.log(records.length);
		
		ResultSet resultSet = Singleton.getInstance().getSQLManager().queryPrepared(
			"SELECT longest_common_substring(?,`description`) AS lcs, description FROM llt WHERE `description` REGEXP ?;",
			new String[] { original, baseToken }
		);
		
		try {
			while (resultSet.next()) {
				matches.add(new ExtendedFuzzyMatch(
					resultSet.getString("lcs"),
					resultSet.getString("description"),
					StringUtils.getJaroWinklerDistance(resultSet.getString("lcs"), resultSet.getString("description")),
					CosineSimilarity.calculate(resultSet.getString("lcs"), resultSet.getString("description"), "//W+"),
					CosineSimilarity.calculate(resultSet.getString("lcs"), resultSet.getString("description"), "(?!^)"),
					null
				));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (Record comparator : this.comparators.read()) {
			LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());
		    solver.add(original);
		    solver.add(comparator.getValue());
			
		    String longestCommonSubstring = solver.getLongestCommonSubstring().toString();
		    
		    if (longestCommonSubstring.indexOf(baseToken) > -1) {
		    	matches.add(new ExtendedFuzzyMatch(
		    		longestCommonSubstring,
		    		comparator.getValue(),
		    		StringUtils.getJaroWinklerDistance(longestCommonSubstring, comparator.getValue()),
		    		CosineSimilarity.calculate(longestCommonSubstring, comparator.getValue(), "//W+"),
		    		CosineSimilarity.calculate(longestCommonSubstring, comparator.getValue(), "(?!^)"),
		    		comparator
		    	));
		    	
		    	/*Console.log(
		    		original,
		    		longestCommonSubstring,
		    		comparator.getValue(),
		    		StringUtils.getJaroWinklerDistance(longestCommonSubstring, comparator.getValue()),
		    		CosineSimilarity.calculate(longestCommonSubstring, comparator.getValue(), "//W+"),
		    		CosineSimilarity.calculate(longestCommonSubstring, comparator.getValue(), "(?!^)")
		    	);*/
		    /*}
		}*/
		
		Console.log("---top 3---");
		Console.log(matches.size());
		//Collections.sort(matches, comparing(ExtendedFuzzyMatch::getJaroWinkler));
		matches.sort(new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				if (((ExtendedFuzzyMatch) o1).getJaroWinkler() < ((ExtendedFuzzyMatch) o2).getJaroWinkler())
					return 1;
				else if (((ExtendedFuzzyMatch) o1).getJaroWinkler() > ((ExtendedFuzzyMatch) o2).getJaroWinkler())
					return -1;
				else
					return 0;
			}
			
		});
		
		for (int i=0; i<3; i++)
			Console.log(matches.get(i).getOriginal(), matches.get(i).getComparator(), matches.get(i).getJaroWinkler(), matches.get(i).getCosineWords(), matches.get(i).getCosineChars());
		
		//return matches.toArray(new FuzzyMatch[matches.size()]);
		
		return false;
	}
	
	public double matches(String original, String comparator, ALGORITHM algorithm) {
		switch (algorithm) {
			case LEVENSHTEIN:
				return StringUtils.getLevenshteinDistance(original, comparator);
			case JAROWINKLER:
				return StringUtils.getJaroWinklerDistance(original, comparator);
			case COSINEWORDS:
				return CosineSimilarity.calculate(original, comparator, "//W+");
			case COSINECHARS:
				return CosineSimilarity.calculate(original, comparator, "(?!^)");
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
			case COSINEWORDS:
				return (CosineSimilarity.calculate(original, comparator, "//W+") >= threshold);
			case COSINECHARS:
				return (CosineSimilarity.calculate(original, comparator, "(?!^)") >= threshold);
			case REGEX:
				return original.matches(comparator);
			default:
				return original.equals(comparator);
		}
	}
	
}
