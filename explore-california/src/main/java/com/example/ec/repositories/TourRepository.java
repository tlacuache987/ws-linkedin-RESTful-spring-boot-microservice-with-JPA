package com.example.ec.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ec.domain.Tour;

public interface TourRepository extends CrudRepository<Tour, Integer> {

}
