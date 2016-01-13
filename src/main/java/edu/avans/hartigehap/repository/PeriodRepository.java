package edu.avans.hartigehap.repository;

import edu.avans.hartigehap.domain.IPeriod;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PeriodRepository extends PagingAndSortingRepository<IPeriod, String> {
}