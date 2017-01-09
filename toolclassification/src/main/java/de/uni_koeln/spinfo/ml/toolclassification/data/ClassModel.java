package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.HashMap;
import java.util.Map;

/**
 * A model using a bag of words as class features.
 * @author jhermes
 *
 */
public class ClassModel {
	
	private int classID;
	
	private int wordCount;
	
	private Map<String, Integer> bagOfWords;
	
	/**
	 * Instantiates a new ClassModel for the specified classId
	 * @param classID
	 */
	public ClassModel(int classID){
		this.classID = classID;
		bagOfWords = new HashMap<String, Integer>();
		wordCount = 0;
	}
	
	/**
	 * Adds the specified word to the bag of words
	 * @param word
	 */
	public void addWord(String word){
		Integer count = bagOfWords.get(word);
		if(count == null){
			count = 0;
		}
		count++;
		bagOfWords.put(word, count);
		wordCount++;
	}
	
	/**
	 * Returns the count of the specified word within the bag of words.
	 * @param word
	 * @return word count
	 */
	public int getWordCount(String word){
		Integer count = bagOfWords.get(word);
		if(count == null){
			count = 0;
		}
		return count;
	}
	
	/** Returns the id of the class of this model.
	 * @return class id
	 */
	public int getClassID(){
		return classID;
	}
	
	/**
	 * Returns the count of all words in the bag of words.
	 * @return
	 */
	public int getOverallWordCount(){
		return wordCount;
	}

}
