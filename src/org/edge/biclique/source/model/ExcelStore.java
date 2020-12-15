package org.edge.biclique.source.model;

public class ExcelStore extends Point3d{
	String chain;
	double resi;
	String resiName;
	String name;
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
	public double getResi() {
		return resi;
	}
	public void setResi(double resi) {
		this.resi = resi;
	}
	public String getResiName() {
		return resiName;
	}
	public void setResiName(String resiName) {
		this.resiName = resiName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
