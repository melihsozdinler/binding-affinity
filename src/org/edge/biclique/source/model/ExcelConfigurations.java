package org.edge.biclique.source.model;

public class ExcelConfigurations {
	
	private String filePath;
	private String excelFilePath;
	private Boolean debug;
	private Integer maxNumberOfThreads;
	private String outputPath;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getExcelFilePath() {
		return excelFilePath;
	}
	public void setExcelFilePath(String excelFilePath) {
		this.excelFilePath = excelFilePath;
	}
	public Boolean getDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public Integer getMaxNumberOfThreads() {
		return maxNumberOfThreads;
	}
	public void setMaxNumberOfThreads(Integer maxNumberOfThreads) {
		this.maxNumberOfThreads = maxNumberOfThreads;
	}
	public String getOutputPath() {
		return outputPath;
	}
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
}
