package com.example.ec.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.ec.domain.Tour;

public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {

	// List<Tour> findByTourPackageCode(@Param("code") String code);

	// page = defaults 0 &
	// size = defaults 20 &
	// sort = entity Id, asc
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable pageable);

	@Override
	// annotation at method annotation
	@RestResource(exported = false)
	<S extends Tour> S save(S entity);

	@Override
	@RestResource(exported = false)
	<S extends Tour> Iterable<S> save(Iterable<S> entities);

	@Override
	@RestResource(exported = false)
	void delete(Integer id);

	@Override
	@RestResource(exported = false)
	void delete(Tour entity);

	@Override
	@RestResource(exported = false)
	void delete(Iterable<? extends Tour> entities);

	@Override
	@RestResource(exported = false)
	void deleteAll();

}
