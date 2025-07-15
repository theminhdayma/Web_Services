package com.data.session06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Bai1_2Controller {

    @GetMapping("/bai1")
    public String bai1() {
        return "bai1";
    }

    @GetMapping("/bai2")
    public String bai2() {
        return "bai2";
    }
}
