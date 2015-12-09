package edu.avans.hartigehap.service;

import java.util.List;

import edu.avans.hartigehap.domain.Reservation;

public interface ReservationService {
	List<Reservation> findAll();
	Reservation save(Reservation reservation);
	Reservation findById(Long id);
}