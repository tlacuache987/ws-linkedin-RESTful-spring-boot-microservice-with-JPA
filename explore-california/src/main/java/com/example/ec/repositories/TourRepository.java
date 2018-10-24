package com.example.ec.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.ec.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {

	// List<Tour> findByTourPackageCode(@Param("code") String code);

	// page = defaults 0 &
	// size = defaults 20 &
	// sort = entity Id, asc
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);
}
