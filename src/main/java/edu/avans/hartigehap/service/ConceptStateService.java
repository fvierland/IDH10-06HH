package edu.avans.hartigehap.service;

import java.util.List;

import edu.avans.hartigehap.domain.ConceptState;
import edu.avans.hartigehap.domain.IReservationState;

public interface ConceptStateService {
	List<ConceptState> findAll();
	IReservationState save(ConceptState state);
}