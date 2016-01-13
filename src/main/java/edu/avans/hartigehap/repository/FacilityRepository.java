package edu.avans.hartigehap.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.avans.hartigehap.domain.IFacility;

public interface FacilityRepository extends PagingAndSortingRepository<IFacility, Long> {
	List<IFacility> findBytype(String type);
}
