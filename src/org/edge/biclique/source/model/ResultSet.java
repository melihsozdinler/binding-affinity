package org.edge.biclique.source.model;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {
	private String experimentCIFModelName;
	private List<String> resultBicliqueCounts;
	private List<String> resultResidueNames;
	private Integer lengthOfSequence;
	private String experimentRealResult;
	
	/**
	 * @return the lengthOfSequence
	 */
	public Integer getLengthOfSequence() {
		return lengthOfSequence;
	}
	/**
	 * @param lengthOfSequence the lengthOfSequence to set
	 */
	public void setLengthOfSequence(Integer lengthOfSequence) {
		this.lengthOfSequence = lengthOfSequence;
	}
	/**
	 * @return the experimentRealResult
	 */
	public String getExperimentRealResult() {
		return experimentRealResult;
	}
	/**
	 * @param experimentRealResult the experimentRealResult to set
	 */
	public void setExperimentRealResult(String experimentRealResult) {
		this.experimentRealResult = experimentRealResult;
	}
	/**
	 * @return the resultBicliqueCounts
	 */
	public List<String> getResultBicliqueCounts() {
		return resultBicliqueCounts;
	}
	/**
	 * @param resultBicliqueCounts the resultBicliqueCounts to set
	 */
	public void setResultBicliqueCounts(List<String> resultBicliqueCounts) {
		this.resultBicliqueCounts = resultBicliqueCounts;
	}
	/**
	 * @return the experimentCIFModelName
	 */
	public String getExperimentCIFModelName() {
		return experimentCIFModelName;
	}
	/**
	 * @param experimentCIFModelName the experimentCIFModelName to set
	 */
	public void setExperimentCIFModelName(String experimentCIFModelName) {
		this.experimentCIFModelName = experimentCIFModelName;
	}
	/**
	 * @return the resultResidueNames
	 */
	public List<String> getResultResidueNames() {
		return resultResidueNames;
	}
	/**
	 * @param resultResidueNames the resultResidueNames to set
	 */
	public void setResultResidueNames(List<String> resultResidueNames) {
		this.resultResidueNames = resultResidueNames;
	}
	
	public void createResultResidueNames() {
		this.resultResidueNames = new ArrayList<String>();
	}
}
