package edu.avans.hartigehap.service;

import java.util.List;


import edu.avans.hartigehap.domain.ConfirmedState;
import edu.avans.hartigehap.domain.IReservationState;

public interface ConfirmedStateService {
	List<ConfirmedState> findAll();
	IReservationState save(ConfirmedState state);
}
