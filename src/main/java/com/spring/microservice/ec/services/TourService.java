package com.spring.microservice.ec.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.microservice.ec.domain.Difficulty;
import com.spring.microservice.ec.domain.Region;
import com.spring.microservice.ec.domain.Tour;
import com.spring.microservice.ec.domain.TourPackage;
import com.spring.microservice.ec.repo.TourPackageRepository;
import com.spring.microservice.ec.repo.TourRepository;

@Service
public class TourService {
	private TourRepository tourRepository;
	private TourPackageRepository tourPackageRepository;

	@Autowired
	public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
		this.tourRepository = tourRepository;
		this.tourPackageRepository = tourPackageRepository;
	} 

	public Tour createTour(String title, String description, String blurb, double price, String duration, String bullets,
			String keywords, String tourPackageName, Difficulty difficulty,
			Region region) {

		
		/*TourPackage tourPackage1 = tourPackageRepository.findById(tourPackageName)
				.orElseThrow(()->new RuntimeException("Tour Package Doesn't exist"));*/
		TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName);
		if (tourPackage == null) {
            throw new RuntimeException("TourPackage " + tourPackage + " not found");
        }

		return tourRepository.save(new Tour(title,description,blurb,price,duration,bullets,
				keywords,tourPackage,difficulty,region));		
	}


	public long total(){ return tourRepository.count(); }

}
