package com.empresa.proyecto.car.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;

import com.empresa.proyecto.car.model.Car;

@Repository
public interface CarRepository extends ReactiveCrudRepository<Car, Long> {

}
