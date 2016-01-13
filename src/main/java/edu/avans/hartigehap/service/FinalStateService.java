package edu.avans.hartigehap.service;

import java.util.List;

import edu.avans.hartigehap.domain.FinalState;
import edu.avans.hartigehap.domain.IReservationState;

public interface FinalStateService {
	List<FinalState> findAll();
	IReservationState save(FinalState state);
}
