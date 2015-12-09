package edu.avans.hartigehap.service;

import java.util.List;
import edu.avans.hartigehap.domain.Reservation;

public interface FinalStateService {
	List<Reservation> findAll();
	Reservation save(Reservation reservation);
	Reservation findById(Long id);
}
