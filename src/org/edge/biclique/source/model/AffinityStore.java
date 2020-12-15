package org.edge.biclique.source.model;

public class AffinityStore extends Point3d{
	
	private String complex;
	private String chainA;
	private String chainB;
	private String chainAFileName;
	private String chainBFileName;
	private String unboundChainA;
	private String unboundChainB;
	private String unboundChainAFileName;
	private String unboundChainBFileName;
		

	public String getChainA() {
		return chainA;
	}
	public void setChainA(String chainA) {
		this.chainA = chainA;
	}

	public String getChainB() {
		return chainB;
	}
	public void setChainB(String chainB) {
		this.chainB = chainB;
	}
	
	public String getChainAFileName() {
		return chainAFileName;
	}
	public void setChainAFileName(String chainAFileName) {
		this.chainAFileName = chainAFileName;
	}
	
	public String getChainBFileName() {
		return chainBFileName;
	}
	public void setChainBFileName(String chainBFileName) {
		this.chainBFileName = chainBFileName;
	}
	
	public String getUnboundChainA() {
		return unboundChainA;
	}
	public void setUnboundChainA(String unboundChainA) {
		this.unboundChainA = unboundChainA;
	}
	
	public String getUnboundChainB() {
		return unboundChainB;
	}
	public void setUnboundChainB(String unboundChainB) {
		this.unboundChainB = unboundChainB;
	}
	
	public String getUnboundChainAFileName() {
		return unboundChainAFileName;
	}
	public void setUnboundChainAFileName(String unboundChainAFileName) {
		this.unboundChainAFileName = unboundChainAFileName;
	}
	
	public String getUnboundChainBFileName() {
		return unboundChainBFileName;
	}
	public void setUnboundChainBFileName(String unboundChainBFileName) {
		this.unboundChainBFileName = unboundChainBFileName;
	}
	/**
	 * @return the complex
	 */
	public String getComplex() {
		return complex;
	}
	/**
	 * @param complex the complex to set
	 */
	public void setComplex(String complex) {
		this.complex = complex;
	}
}
