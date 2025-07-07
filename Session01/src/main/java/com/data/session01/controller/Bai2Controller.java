package com.data.session01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Bai2Controller {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot API!";
    }
}
