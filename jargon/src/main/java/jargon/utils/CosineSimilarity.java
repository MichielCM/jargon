package jargon.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class CosineSimilarity {

    /**
    * @param terms values to analyze
    * @return a map containing unique 
    * terms and their frequency
    */
   private static Map<String, Integer> getTermFrequencyMap(String[] terms) {
       Map<String, Integer> termFrequencyMap = new HashMap<>();
       for (String term : terms) {
           Integer n = termFrequencyMap.get(term);
           n = (n == null) ? 1 : ++n;
           termFrequencyMap.put(term, n);
       }
       return termFrequencyMap;
   }
   
   public static double calculate(String text1, String text2) {
	   return calculate(text1, text2, "(?!^)");
   }

   /**
    * @param text1 
    * @param text2 
    * @return cosine similarity of text1 and text2
    */
   public static double calculate(String text1, String text2, String regex) {
       //Get vectors
       Map<String, Integer> a = getTermFrequencyMap(text1.split(regex)); //\\W+
       Map<String, Integer> b = getTermFrequencyMap(text2.split(regex)); //\\W+

       //Get unique words from both sequences
       HashSet<String> intersection = new HashSet<>(a.keySet());
       intersection.retainAll(b.keySet());

       double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

       //Calculate dot product
       for (String item : intersection) {
           dotProduct += a.get(item) * b.get(item);
       }

       //Calculate magnitude a
       for (String k : a.keySet()) {
           magnitudeA += Math.pow(a.get(k), 2);
       }

       //Calculate magnitude b
       for (String k : b.keySet()) {
           magnitudeB += Math.pow(b.get(k), 2);
       }

       //return cosine similarity
       return dotProduct / Math.sqrt(magnitudeA * magnitudeB);
   }
	
}
