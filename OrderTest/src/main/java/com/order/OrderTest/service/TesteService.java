package com.order.OrderTest.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class TesteService {

    @CircuitBreaker(name = "customCircuit", fallbackMethod = "fallbackMethod")
    public void testFunction() throws IOException {

        System.out.println("Executando testFunction...");

        // Simulando uma exceção verificada
        try {
            OutputStream outputStream = new FileOutputStream("f:/teste.txt");
            Writer writer = new OutputStreamWriter(outputStream);
            BufferedWriter br = new BufferedWriter(writer);

            br.write("Escrevendo no arquivo");
            br.newLine();
            br.write("Another Line");
            br.close();

        }
        catch (IOException e) {

            throw new IOException(e.getMessage());
        }
    }

    public void fallbackMethod(IOException ex) {


        System.out.println(ex.getMessage());
        System.out.println("Cai no fallbackMethod");
    }
}
