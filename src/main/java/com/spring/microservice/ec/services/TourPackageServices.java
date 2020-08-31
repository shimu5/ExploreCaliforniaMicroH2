package com.spring.microservice.ec.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microservice.ec.domain.TourPackage;
import com.spring.microservice.ec.repo.TourPackageRepository;

@Service
public class TourPackageServices {
	

	private TourPackageRepository tourPackageRepository;

	@Autowired
	public TourPackageServices(TourPackageRepository tourPackageRepository) {
		this.tourPackageRepository = tourPackageRepository;
	}
	
	public TourPackage createTourPackage(String code, String name) {
		//return tourPackageRepository.findById(code).orElse(new TourPackage(code, name));
		
		if (!tourPackageRepository.existsById(code)) {
            return tourPackageRepository.save(new TourPackage(code, name));
        }
		return null;
	}
	
	public Iterable<TourPackage> lookup(){
		return tourPackageRepository.findAll();		
	}
    
	/*
	 * Lookup all tour package
	 * 
	 * Return all Tour package
	 * */
	public long total() {
		return tourPackageRepository.count();
	}
}
