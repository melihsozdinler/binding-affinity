package org.edge.biclique.source.model;

public class ExcelMiniStore extends Point3d{
	String chain;
	String resiName;
	Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getChain() {
		return chain;
	}
	public void setChain(String chain) {
		this.chain = chain;
	}
	
	public String getResiName() {
		return resiName;
	}
	public void setResiName(String resiName) {
		this.resiName = resiName;
	}
	
}
