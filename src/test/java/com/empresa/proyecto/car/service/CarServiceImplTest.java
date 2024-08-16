package com.empresa.proyecto.car.service;

import com.empresa.proyecto.car.model.Car;
import com.empresa.proyecto.car.repository.CarRepository;
import com.empresa.proyecto.car.service.impl.CarServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    void findByIdTest() {
        Car car = new Car(1L, "Ferrari", "Ferrari", "Red");
        Mockito.when(carRepository.findById(1L)).thenReturn(Mono.just(car));

        Mono<Car> result = carService.findById(1L);

        StepVerifier.create(result)
                .expectNext(car)
                .verifyComplete();
        Mockito.verify(carRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void deleteByIdTest() {
        Mockito.when(carRepository.deleteById(1L)).thenReturn(Mono.empty());

        Mono<Void> result = carService.deleteById(1L);

        StepVerifier.create(result)
                .verifyComplete();

        Mockito.verify(carRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    void saveTest() {
        Car car = new Car(1L, "Ferrari", "Ferrari", "Red");
        Mockito.when(carRepository.save(car)).thenReturn(Mono.just(car));

        Mono<Car> result = carService.save(car);

        StepVerifier.create(result)
                .expectNext(car)
                .verifyComplete();

        Mockito.verify(carRepository, Mockito.times(1)).save(car);
    }

    @Test
    void updateTest() {
        Car car = new Car(1L, "Ferrari", "Ferrari", "Red");
        Mockito.when(carRepository.save(car)).thenReturn(Mono.just(car));

        Mono<Car> result = carService.update(car);

        StepVerifier.create(result)
                .expectNext(car)
                .verifyComplete();

        Mockito.verify(carRepository, Mockito.times(1)).save(car);
    }

    @Test
    void findAllTest() {
        Car car1 = new Car(1L, "Ferrari", "Ferrari", "Red");
        Car car2 = new Car(2L, "Tesla", "Tesla", "White");
        Mockito.when(carRepository.findAll()).thenReturn(Flux.just(car1, car2));

        Flux<Car> result = carService.findAll();

        StepVerifier.create(result)
                .expectSubscription()
                .expectNext(car1, car2)
                .verifyComplete();

        Mockito.verify(carRepository, Mockito.times(1)).findAll();
    }

    @Test
    void listNumbersTest() {
        Flux<Integer> result = carService.listNumbers();

        StepVerifier.create(result)
                .expectNext(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }
}
