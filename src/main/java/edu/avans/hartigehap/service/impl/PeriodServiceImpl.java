package edu.avans.hartigehap.service.impl;

import com.google.common.collect.Lists;
import edu.avans.hartigehap.domain.*;
import edu.avans.hartigehap.repository.PeriodRepository;
import edu.avans.hartigehap.service.PeriodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("periodService")
@Repository
@Transactional(rollbackFor = StateException.class)
public class PeriodServiceImpl implements PeriodService {
	final Logger logger = LoggerFactory.getLogger(PeriodServiceImpl.class);

	@Autowired
	private PeriodRepository periodRepository;

	@Override
	public List<IPeriod> findAll() {
		return Lists.newArrayList(periodRepository.findAll());
	}
}
