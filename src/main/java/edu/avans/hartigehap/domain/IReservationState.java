package edu.avans.hartigehap.domain;



import javax.persistence.Entity;

//import org.hibernate.annotations.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;


@SuppressWarnings("serial")
@Entity
@Getter @Setter

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")

public abstract class IReservationState extends DomainObject
{
	
	
	protected Reservation reservation;
	public String state;
	
	
	public abstract void makeConcept(Reservation reservation);
	public abstract void makeFinal(Reservation reservation);
	public abstract void makeConfirmed(Reservation reservation);
	
}
