package de.uni_koeln.spinfo.ml.toolclassification.components;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Tokenizes input Strings using non-word-characters as delimiter.
 * @author jhermes
 *
 */
public class Tokenizer {
	
	/**
	 * Tokenizes specified String using non-word-characters as delimiter.
	 * @param toTokenize String to tokenize
	 * @return List of tokens
	 */
	public static List<String> tokenize(String toTokenize){
		String[] words = toTokenize.split("\\W+");
		return Arrays.asList(words);
	}
	
	/**
	 * Tokenizes specified String using non-word-characters as delimiter.
	 * @param toTokenize String to tokenize
	 * @return Map of tokenized types with count
	 */
	public static Map<String, Integer> getTypeCounts(String toTokenize){
		Map<String, Integer> toReturn = new TreeMap<String, Integer>();
		List<String> tokens = tokenize(toTokenize);
		for (String string : tokens) {
			Integer count = toReturn.get(string);
			if(count==null){
				count = 0;
			}
			count++;
			toReturn.put(string, count);
		}
		return toReturn;
	}

}
