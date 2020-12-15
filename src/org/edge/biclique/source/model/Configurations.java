package org.edge.biclique.source.model;

public class Configurations {

	private String filePath;
	private String excelFilePath;
	private Double significanceThreshold;
	private Boolean debug;
	private Integer numberOfBicqliueSteps;
	private Integer maxNumberOfThreads;
	private Integer chopIndexForFiles;
	private Integer beginIndex;
	private Integer endIndex;
	private Integer totalSamples;
	private Boolean considerWeights;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
	
	public String toString() {
		return  "\n * Excel Path: " + excelFilePath + "\n" + 
				" * File Path: " + filePath + "\n" + 
				" * Consider Weights: " + considerWeights + "\n" + 
				" * Total Samples:  " + totalSamples + "\n" + 
				" * Biclique Steps: " + numberOfBicqliueSteps + "\n" +
				" * Max Number of Threads: " + maxNumberOfThreads;
	}
}
