package edu.avans.hartigehap.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import edu.avans.hartigehap.domain.Reservation;
import edu.avans.hartigehap.repository.ReservationRepository;
import edu.avans.hartigehap.service.ReservationService;

@Service("reservationService")
@Repository
@Transactional
public class ReservationServiceImpl implements ReservationService {
	final Logger logger = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	private ReservationRepository reservationRepository;

	@Transactional(readOnly=true)
	public List<Reservation> findAll() {
		return Lists.newArrayList(reservationRepository.findAll());
	}

	@Override
	public Reservation save(Reservation reservation) {
		return this.reservationRepository.save(reservation);
	}

	@Override
	public Reservation findById(Long id) {
		return this.reservationRepository.findById(id);
	}

}
