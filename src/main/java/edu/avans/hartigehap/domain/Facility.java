package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Getter @Setter

public class Facility extends IFacility {

	private static final long serialVersionUID = 1L;

	public Facility() {
		
	}
	
	public Facility(String description, int price){
		super(description, price, VATFactory.getInstance().getVat("withVAT"));
		
	}	

	public double getTotal() {
		return getPrice();
	}

	public String description() {
		return getDescription();
	}
	
	@Override
	public double getTotalWithVAT() {
		double price = getPrice();
		return price + getVat().calculateVat(price);
	}

}
