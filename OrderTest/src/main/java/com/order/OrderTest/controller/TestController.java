package com.order.OrderTest.controller;

import com.order.OrderTest.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;



@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    public TesteService testeService;


    @GetMapping(value = "/up", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> findAllOrder(){

        return testeService.testFunction();


    }
}
