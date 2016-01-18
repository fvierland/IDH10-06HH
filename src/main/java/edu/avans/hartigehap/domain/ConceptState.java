package edu.avans.hartigehap.domain;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
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
		List<ConfirmedState> confirmedStates = confirmedStateService.findAll();
        reservation.setState(confirmedStates.get(0));
        this.reservationService.save(reservation);
	}
}
