package com.example.ec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec.domain.TourPackage;
import com.example.ec.repositories.TourPackageRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class TourPackageService {

	@Autowired
	private TourPackageRepository tourPackageRepository;

	public TourPackage createTourPackage(String code, String name) {
		if (!tourPackageRepository.exists(code)) {
			return tourPackageRepository.save(new TourPackage(code, name));
		} else {
			throw new RuntimeException("Tour package already exists");
		}
	}

	public Iterable<TourPackage> lookupTourPackages() {
		return tourPackageRepository.findAll();
	}

	public TourPackage lookupTourPackage(String code) {
		return tourPackageRepository.findOne(code);
	}

	public long totalTourPackages() {
		return tourPackageRepository.count();
	}

}
