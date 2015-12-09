package edu.avans.hartigehap.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.avans.hartigehap.domain.ConfirmedState;
import edu.avans.hartigehap.repository.ConfirmedStateRepository;
import edu.avans.hartigehap.service.ConfirmedStateService;

@Service("confirmedStateService")
@Repository
@Transactional
public class ConfirmedStateServiceImpl implements ConfirmedStateService {
	final Logger logger = LoggerFactory.getLogger(ConfirmedStateServiceImpl.class);

	@Autowired
	private ConfirmedStateRepository stateRepository;

	@Transactional(readOnly=true)
	public List<ConfirmedState> findAll() {
		return Lists.newArrayList(stateRepository.findAll());
	}

	@Override
	public ConfirmedState save(ConfirmedState state) {
		return this.stateRepository.save(state);
	}
}