package edu.avans.hartigehap.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Configurable;

@SuppressWarnings("serial")
@Entity
@Configurable
@DiscriminatorValue("FINAL")
public class FinalState extends IReservationState {

    @Transient
    private Reservation reservation;

    public void setReservation(Reservation reservation)
    {
        this.reservation = reservation;
    }
    
   // functie om de status op te halen moet nog toegevoegd worden   
    

    @Override
    public void makeFinal(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Already in finalstate");
    }
    
	@Override
	public void makeConcept(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Not allowed to change to concept when in finalstate");
		
	}
		
	@Override
	public void makeConfirmed(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Not allowed to change to confirmed when in finalstate");
	}
       
}
