package com.data.session09.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/error-demo")
public class ErrorDemoController {

    @GetMapping("/divide")
    public String divide(@RequestParam int a, @RequestParam int b) {
        int result = a / b;
        return "Kết quả: " + result;
    }
}
