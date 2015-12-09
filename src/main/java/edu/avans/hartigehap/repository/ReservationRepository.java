package edu.avans.hartigehap.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import edu.avans.hartigehap.domain.Reservation;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    public Reservation findById(Long id);
}
