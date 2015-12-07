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
    public void makeFinal(Reservation reservation) {
    // TODO Auto-generated method stub
    }
    
	@Override
	public void makeConcept(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void makeConfirmed(Reservation reservation) {
		// TODO Auto-generated method stub
		
	}
}
