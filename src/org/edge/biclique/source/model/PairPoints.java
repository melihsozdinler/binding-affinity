package org.edge.biclique.source.model;

public class PairPoints {
	
	String sourcePair;
	String targetPair;
	
	Point3d sourcePoint;
	Point3d targetPoint;
	
	int sourceIndex;
	int targetIndex;
	
	public String getSourcePair() {
		return sourcePair;
	}
	public void setSourcePair(String sourcePair) {
		this.sourcePair = sourcePair;
	}
	public String getTargetPair() {
		return targetPair;
	}
	public void setTargetPair(String targetPair) {
		this.targetPair = targetPair;
	}
	public Point3d getSourcePoint() {
		return sourcePoint;
	}
	public void setSourcePoint(Point3d sourcePoint) {
		this.sourcePoint = sourcePoint;
	}
	public Point3d getTargetPoint() {
		return targetPoint;
	}
	public void setTargetPoint(Point3d targetPoint) {
		this.targetPoint = targetPoint;
	}
	public int getSourceIndex() {
		return sourceIndex;
	}
	public void setSourceIndex(int sourceIndex) {
		this.sourceIndex = sourceIndex;
	}
	public int getTargetIndex() {
		return targetIndex;
	}
	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}
}
