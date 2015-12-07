package edu.avans.hartigehap.domain;



public abstract class RoomDecorator extends Facility {
	private static final long serialVersionUID = 1L;
	
	public Facility facility;
	public abstract String getDescription();
	public abstract double getPrice();
	
	
	
}