package org.edge.biclique.source.model;

import java.util.concurrent.ConcurrentHashMap;

public class Configurations {

	private String filePath;
	private String excelFilePath;
	private Double significanceThreshold;
	private Double significanceThresholdMax = Double.MAX_VALUE;
	private Boolean debug;
	private Integer numberOfBicqliueSteps;
	private Integer maxNumberOfThreads;
	private Integer chopIndexForFiles;
	private Integer beginIndex;
	private Integer endIndex;
	private Integer totalSamples;
	private Boolean considerWeights;
	private Boolean considerWeightsWithSize;
	private Boolean distanceMultiplier;
	private Boolean residueBased;
	private Boolean residueEnergyMultiplier;
	private ConcurrentHashMap<String, Double> residueEnergies;
	private ConcurrentHashMap<String, ResiduePolarProperty> residuePolarProperty = new ConcurrentHashMap<String, ResiduePolarProperty>();
	private ConcurrentHashMap<String, ResidueChargedProperty> residueChargedProperty = new ConcurrentHashMap<String, ResidueChargedProperty>();
	private String propertyOne;
	private String propertyTwo;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Double getSignificanceThresholdMax() {
		return significanceThresholdMax;
	}
	public void setSignificanceThresholdMax(Double significanceThresholdMax) {
		this.significanceThresholdMax = significanceThresholdMax;
	}
	public Double getSignificanceThreshold() {
		return significanceThreshold;
	}
	public void setSignificanceThreshold(Double significanceThreshold) {
		this.significanceThreshold = significanceThreshold;
	}
	public Boolean getDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public Integer getNumberOfBicqliueSteps() {
		return numberOfBicqliueSteps;
	}
	public void setNumberOfBicqliueSteps(Integer numberOfBicqliueSteps) {
		this.numberOfBicqliueSteps = numberOfBicqliueSteps;
	}
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public Integer getMaxNumberOfThreads() {
		return maxNumberOfThreads;
	}
	public void setMaxNumberOfThreads(Integer maxNumberOfThreads) {
		this.maxNumberOfThreads = maxNumberOfThreads;
	}
	public Integer getChopIndexForFile() {
		return chopIndexForFiles;
	}
	public void setChopIndexForFiles(Integer chopIndexForFiles) {
		this.chopIndexForFiles = chopIndexForFiles;
	}
	/**
	 * @return the beginIndex
	 */
	public Integer getBeginIndex() {
		return beginIndex;
	}
	/**
	 * @param beginIndex the beginIndex to set
	 */
	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}
	/**
	 * @return the endIndex
	 */
	public Integer getEndIndex() {
		return endIndex;
	}
	/**
	 * @param endIndex the endIndex to set
	 */
	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**
	 * @return the totalSamples
	 */
	public Integer getTotalSamples() {
		return totalSamples;
	}
	/**
	 * @param totalSamples the totalSamples to set
	 */
	public void setTotalSamples(Integer totalSamples) {
		this.totalSamples = totalSamples;
	}
	/**
	 * @return the considerWeights
	 */
	public Boolean getConsiderWeights() {
		return considerWeights;
	}
	/**
	 * @param considerWeights the considerWeights to set
	 */
	public void setConsiderWeights(Boolean considerWeights) {
		this.considerWeights = considerWeights;
	}
	

	public Boolean getConsiderWeightsWithSize() {
		return considerWeightsWithSize;
	}
	
	/**
	 * @param considerWeightsWithSize the considerWeights with Size of biclique to set
	 */
	public void setConsiderWeightsWithSize(Boolean considerWeightsWithSize) {
		this.considerWeightsWithSize = considerWeightsWithSize;
	}
	
	public String toString() {
		return  "\n * Excel Path: " + excelFilePath + "\n" + 
				" * File Path: " + filePath + "\n" + 
				" * Consider Weights: " + considerWeights + "\n" + 
				" * Consider Weights with Size: " + considerWeightsWithSize + "\n" + 
				" * Total Samples:  " + totalSamples + "\n" + 
				" * Biclique Steps: " + numberOfBicqliueSteps + "\n" +
				" * Max Number of Threads: " + maxNumberOfThreads;
	}
	public Boolean getDistanceMultiplier() {
		return distanceMultiplier;
	}
	public void setDistanceMultiplier(Boolean distanceMultiplier) {
		this.distanceMultiplier = distanceMultiplier;
	}
	public Boolean getResidueBased() {
		return residueBased;
	}
	public void setResidueBased(Boolean residueBased) {
		this.residueBased = residueBased;
	}
	public ConcurrentHashMap<String, Double> getResidueEnergies() {
		return residueEnergies;
	}
	public void setResidueEnergies(ConcurrentHashMap<String, Double> residueEnergies) {
		this.residueEnergies = residueEnergies;
	}
	public Boolean getResidueEnergyMultiplier() {
		return residueEnergyMultiplier;
	}
	public void setResidueEnergyMultiplier(Boolean residueEnergyMultiplier) {
		this.residueEnergyMultiplier = residueEnergyMultiplier;
	}
	public ConcurrentHashMap<String, ResiduePolarProperty> getResiduePolarProperty() {
		return residuePolarProperty;
	}
	public void setResiduePolarProperty(ConcurrentHashMap<String, ResiduePolarProperty> residueProperty) {
		this.residuePolarProperty = residueProperty;
	}
	public ConcurrentHashMap<String, ResidueChargedProperty> getResidueChargedProperty() {
		return residueChargedProperty;
	}
	public void setResidueChargedProperty(ConcurrentHashMap<String, ResidueChargedProperty> residueChargedProperty) {
		this.residueChargedProperty = residueChargedProperty;
	}
	public String getPropertyOne() {
		return propertyOne;
	}
	public void setPropertyOne(String propertyOne) {
		this.propertyOne = propertyOne;
	}
	public String getPropertyTwo() {
		return propertyTwo;
	}
	public void setPropertyTwo(String propertyTwo) {
		this.propertyTwo = propertyTwo;
	}
}
