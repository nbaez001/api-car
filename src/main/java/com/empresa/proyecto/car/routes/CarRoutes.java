package com.empresa.proyecto.car.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CarRoutes {

    @Bean
    public RouterFunction<ServerResponse> routes(CarHandler handler) {
        return RouterFunctions.route(GET("/car").and(accept(MediaType.TEXT_EVENT_STREAM)), req -> handler.getAll())
                .andRoute(GET("/car/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::getOne)
                .andRoute(POST("/car").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(PUT("/car").and(accept(MediaType.APPLICATION_JSON)), handler::save)
                .andRoute(DELETE("/car/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteById)
                .andRoute(GET("/car/numbers/valor").and(accept(MediaType.TEXT_EVENT_STREAM)), req -> handler.getListNumbers());
    }
}
