package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class Facility extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	
	private String description;
	private double price;

	public String getDescription() {
		return description;
	}

	public double getPrice() {
		return price;
	}
	
	
		
		
	
}