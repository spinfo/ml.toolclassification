package de.uni_koeln.spinfo.ml.toolclassification.components.crossvalidation;

import java.util.List;

/**
 * Class to bundle trainings and test sets applicable 
 * to machine learning techniques
 * @author jhermes
 *
 * @param <T>
 */
public class TrainingTestSets<T>{
	private List<T> training;
	private List<T> test;
	
	public TrainingTestSets(List<T> training, List<T> test) {
		super();
		this.training = training;
		this.test = test;
	}
	
	public List<T> getTrainingSet() {
		return training;
	}
	
	public List<T> getTestSet() {
		return test;
	}	
		
}
