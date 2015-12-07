package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter

public abstract class RoomOptions extends RoomDecorator {
	private static final long serialVersionUID = 1L;
	
	String hasWifi;
	String hasMenu;
	String hasBeamer;
	String hasDecoration;
	
	
	
	public RoomOptions(Facility facility) {
		this.facility = facility;
	}

	public String getDescription() {
		return facility.getDescription();
	}

	public double cost() {
		return facility.getPrice();
	}
	
	
}