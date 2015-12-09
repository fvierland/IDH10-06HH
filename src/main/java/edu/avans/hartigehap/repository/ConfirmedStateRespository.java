package edu.avans.hartigehap.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.avans.hartigehap.domain.ConceptState;

public interface ConfirmedStateRespository extends PagingAndSortingRepository<ConceptState, Long> {

}
