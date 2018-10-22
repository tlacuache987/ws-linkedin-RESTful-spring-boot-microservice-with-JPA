package com.example.ec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import com.example.ec.repositories.TourRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TourService {

	@Autowired
	private TourPackageService tourPackageService;
	
	@Autowired
	private TourRepository tourRepository;

	public Tour createTour(String title, String description, String blurb, 
						   Integer price, String duration, String bullets, 
						   String keywords, String tourPackageCode, 
						   Difficulty difficulty, Region region){
		
		TourPackage tourPackage = tourPackageService.lookupTourPackage(tourPackageCode);
		
		if(tourPackage == null) {
			throw new RuntimeException("Tour package "+tourPackageCode+" doesn't exist");
		}
		
		Tour tour = Tour.builder().
				title(title).
				description(description).
				blurb(blurb).
				price(price).
				duration(duration).
				bullets(bullets).
				keywords(keywords).
				tourPackage(tourPackage).
				difficulty(difficulty).
				region(region).
				build();
		
		return tourRepository.save(tour);
	}

	public Iterable<Tour> lookupTours() {
		return tourRepository.findAll();
	}

	public long totalTours() {
		return tourRepository.count();
	}

}
