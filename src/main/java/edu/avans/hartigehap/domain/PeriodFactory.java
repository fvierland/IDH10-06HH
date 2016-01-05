package edu.avans.hartigehap.domain;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class PeriodFactory {

    private static PeriodFactory _instance;

    private PeriodFactory () {}

    public static PeriodFactory getInstance () {
        if (_instance == null) {
        	_instance = new PeriodFactory();
        }
    	return _instance;
    }
	
	public List<IPeriod> buildPeriod(DateTime start, DateTime end, Reservation reservation) {
		List<IPeriod> periods = new ArrayList<IPeriod>();
		int reservedDays = Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay() ).getDays();
		int startHour = start.getHourOfDay();
		int endHour = end.getHourOfDay();
		
		boolean firstDay = true;
		boolean lastDay = false;
		DateTime reservationDate = start;
		for ( int i = reservedDays; i >= 0; i--) {
			if (i == 0) 
				lastDay = true;
			
			if (firstDay) {
				firstDay = false;
				boolean oneDayReservation = start.dayOfYear().get() == end.dayOfYear().get() && start.year().get() == end.year().get();
				
				if (isMorning(startHour)) {
					periods.add(new Morning(reservationDate, reservation));
					if (oneDayReservation) {
						if (isEvening(endHour)) {
							periods.add(new Afternoon(reservationDate, reservation));
							periods.add(new Evening(reservationDate, reservation));
						} else if (isAfternoon(endHour)) {
							periods.add(new Afternoon(reservationDate, reservation));
						}
					}
				} else if (isAfternoon(startHour)) {
					periods.add(new Afternoon(reservationDate, reservation));
					if (oneDayReservation) {
						if (isEvening(endHour)) {
							periods.add(new Evening(reservationDate, reservation));
						}
					}
				} else if (isEvening(startHour)) {
					periods.add(new Evening(reservationDate, reservation));
				}
			}
			else if (lastDay) {
				if (isMorning(endHour)) {
					periods.add(new Morning(reservationDate, reservation));
				} else if (isAfternoon(endHour)) {
					periods.add(new Morning(reservationDate, reservation));
					periods.add(new Afternoon(reservationDate, reservation));
				} else if (isEvening(endHour)) {
					periods.add(new Morning(reservationDate, reservation));
					periods.add(new Afternoon(reservationDate, reservation));
					periods.add(new Evening(reservationDate, reservation));
				}
				
			} else {
				//whole days
				periods.add(new Morning(reservationDate, reservation));
				periods.add(new Afternoon(reservationDate, reservation));
				periods.add(new Evening(reservationDate, reservation));
			}
			reservationDate = reservationDate.plusDays(1);
		}
		
		return periods;
	}
	
	private boolean isMorning (int startHour) {
		return isBetween(startHour, 8, 12);
	}
	
	private boolean isAfternoon (int startHour) {
		return isBetween(startHour, 13, 18);	
	}	
	
	private boolean isEvening (int startHour) {
		return isBetween(startHour, 19, 23);
	}
	
	
	private boolean isBetween(int x, int lower, int upper) {
		  return lower <= x && x <= upper;
	}
}
