package edu.avans.hartigehap.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.avans.hartigehap.domain.IFacility;
import edu.avans.hartigehap.repository.FacilityRepository;
import edu.avans.hartigehap.service.FacilityService;

@Service("facilityService")
@Repository
@Transactional
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityRepository facilityRepository;
	
	@Override
	@Transactional(readOnly=true)
	public IFacility findById(Long id) {
		return facilityRepository.findOne(id);
	}

	@Override
	public List<IFacility> findAll() {
		return Lists.newArrayList(facilityRepository.findAll());
	}

	@Override
	public IFacility save(IFacility facility) {
		return this.facilityRepository.save(facility);
	}

	@Override
	public List<IFacility> findByType(String type) {
		return Lists.newArrayList(facilityRepository.findBytype(type));
	}

}
