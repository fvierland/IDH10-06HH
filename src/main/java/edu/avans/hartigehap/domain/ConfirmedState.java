package edu.avans.hartigehap.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Configurable;

@Entity
@Configurable
@DiscriminatorValue("CONFIRMED")
public class ConfirmedState extends IReservationState {

    @Transient
    private Reservation reservation;

   /* @Override
    public void makeFinal(Reservation reservation) {
       List<FinalStatus> finalStatuses = finalStatusService.findAll();
        reservation.setStatus(finalStatuses.get(0));
        this.reservationService.save(reservation);
    }
    */

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
