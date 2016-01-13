package edu.avans.hartigehap.service;

import java.util.List;

import edu.avans.hartigehap.domain.IFacility;

public interface FacilityService {
	IFacility findById(Long id);
	List<IFacility> findAll();
	List<IFacility> findByType(String type);
	IFacility save(IFacility facility);
}
