package edu.avans.hartigehap.domain;

import javax.persistence.Entity;
import org.joda.time.DateTime;


@Entity
public class Morning extends IPeriod{
	private static final long serialVersionUID = 1L;
	
	
	public Morning() {}
	
	public Morning (DateTime date, Reservation reservation) {
		this.date = date.toLocalDate();
		this.reservation = reservation;
	}
	
	@Override
	public void setDate(DateTime date) {
		this.date = date.toLocalDate();
	}
 
}