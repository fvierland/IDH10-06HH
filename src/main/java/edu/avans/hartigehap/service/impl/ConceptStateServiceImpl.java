package edu.avans.hartigehap.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.avans.hartigehap.domain.ConceptState;
import edu.avans.hartigehap.repository.ConceptStateRepository;
import edu.avans.hartigehap.service.ConceptStateService;

@Service("conceptStateService")
@Repository
@Transactional
public class ConceptStateServiceImpl implements ConceptStateService {
	final Logger logger = LoggerFactory.getLogger(ConceptStateServiceImpl.class);

	@Autowired
	private ConceptStateRepository stateRepository;

	@Transactional(readOnly=true)
	public List<ConceptState> findAll() {
		return Lists.newArrayList(stateRepository.findAll());
	}

	@Override
	public ConceptState save(ConceptState state) {
		return this.stateRepository.save(state);
	}
}
