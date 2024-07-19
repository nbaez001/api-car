package com.empresa.proyecto.car.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.empresa.proyecto.car.model.Car;
import com.empresa.proyecto.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cars2")
public class CarController {

	@Autowired
	private CarService carService;

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Car> getAll() {
		return carService.findAll(Sort.by("model", "brand"));
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Car> getOne(@PathVariable Long id) {
		return carService.findById(id).switchIfEmpty(Mono.error(() -> new ResponseStatusException(NOT_FOUND)));
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Car> save(@RequestBody Mono<Car> car) {
		return car.flatMap(carService::save);
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Car> update(@RequestBody Mono<Car> car) {
		return car.flatMap(carService::update);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<Void> delete(@PathVariable Long id) {
		return carService.deleteById(id);
	}

	@GetMapping(value = "/numbers/valor", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Integer> getListNumbers() {
		return carService.listNumbers();
	}
}
