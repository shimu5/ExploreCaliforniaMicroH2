package com.spring.microservice.ec.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.spring.microservice.ec.domain.TourPackage;

public interface TourPackageRepository extends CrudRepository<TourPackage, String>{

	TourPackage findByName(@Param("name") String name);
	
}
