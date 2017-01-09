package de.uni_koeln.spinfo.ml.toolclassification.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import de.uni_koeln.spinfo.ml.toolclassification.data.Tool;

/**
 * Builds feature vectors for Tools from their contexts
 * @author jhermes
 *
 */
public class VectorBuilder {
	
	private Map<Tool, int[]> toolsWithVectors;
	
	/**
	 * Initializes a VectorBuilder for the specified List of Tools
	 * @param toolsWithContexts
	 */
	public VectorBuilder(List<Tool> toolsWithContexts){
		this.buildVectors(toolsWithContexts, getTypesList(toolsWithContexts));
	}
	
	private List<String> getTypesList(List<Tool> toolsWithContexts){
		Set<String> types = new TreeSet<String>();
		for (Tool tool : toolsWithContexts) {
			List<String> words = Tokenizer.tokenize(tool.getContext());
			for (String word : words) {
				types.add(word);
			}
		}
		return new ArrayList<String>(types);
	} 
	
	private void buildVectors(List<Tool> toolsWithContexts, List<String> types){
		toolsWithVectors = new HashMap<Tool, int[]>();
		for (Tool tool : toolsWithContexts) {
			Map<String, Integer> typeCounts = Tokenizer.getTypeCounts(tool.getContext());
			int[] vector = new int[types.size()];
			for (int i=0; i<types.size(); i++) {
				Integer count = typeCounts.get(types.get(i));
				if(count!=null){
					vector[i] = count;
				}
			}
			toolsWithVectors.put(tool, vector);
		}
	}	
	
	/**
	 * Returns the feature vector for the specified Tool
	 * @param tool 
	 * @return feature vector for the specified Tool
	 */
	public int[] getVector(Tool tool){
		return toolsWithVectors.get(tool);
	}
}
