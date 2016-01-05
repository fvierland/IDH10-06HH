package edu.avans.hartigehap.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
@Table(name = "Rooms") 
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)

public abstract class IFacility extends DomainObject {
	private static final long serialVersionUID = 1L;
	
	private int price;
	
	private String description;
	
    @Column(name = "DTYPE", insertable = false, updatable = false)
    private String type;
	
	@OneToMany(mappedBy="facility")
	private List<Reservation> reservation;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Vat vat;

	
	public IFacility(){
		
	}
	
	public IFacility(String description, int price, Vat vat)
	{
		this.price = price;
		this.description = description;
		this.vat = vat;
		
	}
	
	public abstract double getTotal();
	public abstract double getTotalWithVAT();
	public abstract String description();
	
}
