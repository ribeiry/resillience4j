package com.order.OrderTest.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

@Configuration
public class CircuitBreakerConfiguration {

    @Bean
    public CircuitBreaker customCircuitBreaker(CircuitBreakerRegistry circuitBreakerRegistry) {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(100)
                .permittedNumberOfCallsInHalfOpenState(2)
                .slidingWindowSize(1)
                .waitDurationInOpenState(Duration.ofMillis(10000))
                .permittedNumberOfCallsInHalfOpenState(1)
                .minimumNumberOfCalls(1)
                .recordExceptions(TimeoutException.class)
                .ignoreExceptions(java.io.IOException.class,java.io.FileNotFoundException.class)
                .build();

        return circuitBreakerRegistry.circuitBreaker("customCircuit",circuitBreakerConfig);
    }
    @Bean
    public CircuitBreakerRegistry CircuitBreakerRegistry(){
        return CircuitBreakerRegistry.ofDefaults();
    }

}
