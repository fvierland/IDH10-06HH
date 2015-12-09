package edu.avans.hartigehap.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;



@Entity
@DiscriminatorValue("CONCEPT")
public class ConceptState extends IReservationState {

    @Transient
    private Reservation reservation;

    @Override
    public void makeConcept(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Already in concept state");
    }
    
    @Override
    public void makeFinal(Reservation reservation)throws InvalidStateException{
        throw new InvalidStateException("First confirm reservation!");
    }
			
	
	@Override
	public void makeConfirmed(Reservation reservation) {
		// functie om status van concept naar confirmed te zetten
	}
}
