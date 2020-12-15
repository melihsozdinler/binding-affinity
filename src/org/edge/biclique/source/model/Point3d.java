package org.edge.biclique.source.model;

public class Point3d {

    private double xCoord;
    private double yCoord;
    private double zCoord;


    public Point3d(double x, double y, double z){
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    public Point3d(){
        this (0,0,0);
    }

    public double getxCoord() {
        return xCoord;
    }
    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }
    public double getyCoord() {
        return yCoord;
    }
    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }
    public double getzCoord() {
        return zCoord;
    }
    public void setzCoord(double zCoord) {
        this.zCoord = zCoord;
    }

    public double distanceTo(Point3d other) {
        return Math.sqrt(Math.pow(this.xCoord-other.getxCoord(), 2)
                + Math.pow(this.yCoord-other.getyCoord(), 2)
                + Math.pow(this.zCoord-other.getzCoord(), 2));
    }
    
    public Point3d getPoint3d() {
    	return this;
    }
 }