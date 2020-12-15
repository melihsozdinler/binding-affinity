package org.edge.biclique.source.model;

public class ResultConfiguration {
	private String filePath;
	private String fileName;
	private String prefix;
	private Boolean debug;
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}
	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	/**
	 * @return the debug
	 */
	public Boolean getDebug() {
		return debug;
	}
	/**
	 * @param debug the debug to set
	 */
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
}
