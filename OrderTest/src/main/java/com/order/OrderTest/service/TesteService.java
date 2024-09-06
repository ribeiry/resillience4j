package com.order.OrderTest.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TesteService {

    @CircuitBreaker(name = "customCircuit", fallbackMethod = "fallbackMethod")
    public ResponseEntity<String> testFunction()  {

        System.out.println("Executando testFunction...");

        // Simulando uma exceção verificada
        if (Math.random() > 0.5) {
            throw new RuntimeException("Falha no serviço externo");
        }
        return ResponseEntity.status(200).body("Resposta do serviço externo");
    }

    public ResponseEntity<String> fallbackMethod(Throwable ex) {


        System.out.println(ex.getMessage());
        System.out.println("Cai no fallbackMethod");

        return ResponseEntity.status(200).body("Response do fallback");
    }
}
