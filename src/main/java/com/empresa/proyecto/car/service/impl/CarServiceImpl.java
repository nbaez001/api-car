package com.empresa.proyecto.car.service.impl;

import com.empresa.proyecto.car.model.Car;
import com.empresa.proyecto.car.repository.CarRepository;
import com.empresa.proyecto.car.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	public Mono<Car> findById(Long id) {
		return carRepository.findById(id).doOnNext(p -> log.info("Car with id " + p.getId()));
	}

	public Mono<Void> deleteById(Long id) {
		return carRepository.deleteById(id).doOnNext(c -> log.info("Car with id {} deleted", id));
	}

	public Mono<Car> save(Car car) {
		return carRepository.save(car);
	}

	public Mono<Car> update(Car car) {
		return carRepository.save(car);
	}

	public Flux<Car> findAll() {
		return carRepository.findAll();
	}

	public Flux<Integer> listNumbers() {
		return Flux.create(fluxSick -> {
			for (int i = 1; i <= 10; i++) {
				fluxSick.next(i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			fluxSick.complete();
		});
	}
}
