package edu.avans.hartigehap.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public abstract class RoomDecorator extends IFacility {

	private static final long serialVersionUID = 1L;
	
	public RoomDecorator(){
		
	}
	
	public RoomDecorator(IFacility ifacility, int price, Vat vat){
		super(ifacility.getDescription(), price, vat);
		setIfacility(ifacility);
	}
	
	@OneToOne(cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name="decorator")
	
	public IFacility ifacility;
}