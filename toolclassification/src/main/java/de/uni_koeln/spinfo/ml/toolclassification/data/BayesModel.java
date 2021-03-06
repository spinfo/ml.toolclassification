package de.uni_koeln.spinfo.ml.toolclassification.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A model to store models from different classes 
 * and for the calculation of class membership probabilities
 * based on a Naive Bayes classificator. 
 * @author jhermes
 *
 */
public class BayesModel {
	
	private Map<Integer, ClassModel> models;
	
	private int wordCount;
	
	/**
	 * Instantiates a new BayesModel.
	 */
	public BayesModel(){
		models = new TreeMap<Integer, ClassModel>();
		wordCount=0;
	}
	
	/**
	 * Adds the specified word to the specified class bag of words.
	 * @param word
	 * @param classID
	 */
	public void addWordToClass(String word, int classID){
		ClassModel model = models.get(classID);
		if(model == null){
			model = new ClassModel(classID);
		}
		model.addWord(word);
		models.put(classID, model);
		wordCount++;
	}
	
	/** Calculates the best matching class for the specified bagOfWords
	 * @param words
	 * @return class membership with highest probability
	 */
	public int getClassification(List<String> words){
		List<Double> classValues = new ArrayList<Double>();
		Set<Integer> keySet = models.keySet();
		for (Integer integer : keySet) {
			classValues.add(models.get(integer).getOverallWordCount()/(double)this.wordCount);
		}
		for (String word : words) {
			List<Double> values = calculateClassProbs(word);
			for(int i=0; i<values.size();i++){
				double result = classValues.get(i)*values.get(i);
				classValues.set(i, result);
			}
		}
		int maxProbabilityPosition = getMaxProbabilityPosition(classValues);		
		return maxProbabilityPosition+1; 
	}
	
	
	private List<Double> calculateClassProbs(String word){
		List<Double> toReturn = new ArrayList<Double>();
		Set<Integer> classIDs = models.keySet();
		for (Integer classID : classIDs) {
			Set<Integer> classIDs2 = models.keySet();
			int proCount = 1;
			int contraCount = 1;
			for (Integer classID2 : classIDs2) {
				int wordCount = models.get(classID2).getWordCount(word);
				if(classID==classID2){
					proCount += wordCount;
				}
				else{
					contraCount += wordCount;
				}
			}
			toReturn.add(proCount/(double)(contraCount));
		}
		return toReturn;
	}
	
	private int getMaxProbabilityPosition(List<Double> values) {
		int max = -1;
		double maxValue = 0.0;
		for (int i=0; i<values.size(); i++) {
			double currentValue = values.get(i);
			if(currentValue>maxValue){
				maxValue=currentValue;
				max=i;
			}
		}
		return max;
	}

}
