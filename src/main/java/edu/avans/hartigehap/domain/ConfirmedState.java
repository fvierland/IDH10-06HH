package edu.avans.hartigehap.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Configurable;

@SuppressWarnings("serial")
@Entity
@Configurable
@DiscriminatorValue("CONFIRMED")

public class ConfirmedState extends IReservationState {

    @Transient
    private Reservation reservation;

    @Override
    public void makeFinal(Reservation reservation) {
    	List<FinalState> finalStates = finalStateService.findAll();
        reservation.setState(finalStates.get(0));
        this.reservationService.save(reservation);
    }
    
	@Override
	public void makeConcept(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Not allowed to change to concept when in confirmedstate");
	}
	
	@Override
	public void makeConfirmed(Reservation reservation) throws InvalidStateException{
        throw new InvalidStateException("Already in confirmedstate");
	}
    	    
}
