package com.spring.microservice.ec.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.spring.microservice.ec.domain.Tour;

//public interface TourRepository extends CrudRepository<Tour,String>{

public interface TourRepository extends PagingAndSortingRepository<Tour,String>{
     	
	 /*List<Tour> findByTourPackageCode(@Param("code") String code);*/
	 
	Page<Tour> findByTourPackageCode(@Param("code") String code, Pageable page);

	@Override
	@RestResource(exported=false)
	<S extends Tour> S save(S entity);
	
	@Override
	@RestResource(exported=false)
	<S extends Tour> Iterable<S> saveAll(Iterable<S> entities);

	@Override
	@RestResource(exported=false)
	void deleteById(String id);

	@Override
	@RestResource(exported=false)
	void delete(Tour entity);

	@Override
	@RestResource(exported=false)
	void deleteAll(Iterable<? extends Tour> entities);

	@Override
	@RestResource(exported=false)
    void deleteAll();
	 
}
