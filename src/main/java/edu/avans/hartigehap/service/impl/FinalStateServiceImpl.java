package edu.avans.hartigehap.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Lists;
import edu.avans.hartigehap.domain.FinalState;
import edu.avans.hartigehap.repository.FinalStateRepository;
import edu.avans.hartigehap.service.FinalStateService;

@Service("finalStateService")
@Repository
@Transactional
public class FinalStateServiceImpl implements FinalStateService {
	final Logger logger = LoggerFactory.getLogger(FinalStateServiceImpl.class);

	@Autowired
	private FinalStateRepository stateRepository;

	@Transactional(readOnly=true)
	public List<FinalState> findAll() {
		return Lists.newArrayList(stateRepository.findAll());
	}

	@Override
	public FinalState save(FinalState state) {
		return this.stateRepository.save(state);
	}

	}

