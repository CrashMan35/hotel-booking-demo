package com.david.hotelbooking.hotel.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hotel")
public class TestController {

    @Value("${hotel.amount}")
    private Integer amount;

    @RequestMapping("/producer")
    public String test(String name) {
        return "I am " + name + amount;
    }
}
