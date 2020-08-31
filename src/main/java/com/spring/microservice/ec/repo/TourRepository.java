package com.spring.microservice.ec.repo;

import org.springframework.data.repository.CrudRepository;

import com.spring.microservice.ec.domain.Tour;

public interface TourRepository extends CrudRepository<Tour,String>{

}
