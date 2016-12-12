package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.HashMap;
import java.util.Map;

public class ClassModel {
	
	private int classID;
	
	private int wordCount;
	
	private Map<String, Integer> bagOfWords;
	
	public ClassModel(int classID){
		this.classID = classID;
		bagOfWords = new HashMap<String, Integer>();
		wordCount = 0;
	}
	
	public void addWord(String word){
		Integer count = bagOfWords.get(word);
		if(count == null){
			count = 0;
		}
		count++;
		bagOfWords.put(word, count);
		wordCount++;
	}
	
	public int getWordCount(String word){
		Integer count = bagOfWords.get(word);
		if(count == null){
			count = 0;
		}
		return count;
	}
	
	public int getClassID(){
		return classID;
	}
	
	public int getOverallWordCount(){
		return wordCount;
	}

}
