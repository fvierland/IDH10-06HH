package edu.avans.hartigehap.domain;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public class WifiDecorator extends RoomDecorator {

	private static final long serialVersionUID = 1L;

	public WifiDecorator(){
		
	}

	public WifiDecorator(IFacility ifacility, int price){
		super(ifacility, price, VATFactory.getInstance().getVat("withVAT"));		
	}
	
	public double getTotal() {
		return getIfacility().getTotal() + getPrice();
	}


	public String description() {
		return getIfacility().description() + ", " + "Wifi";
	}
	
	@Override
	public double getTotalWithVAT() {
		double price = getPrice();
		return price + getVat().calculateVat(price) + getIfacility().getTotal();
	}

}
