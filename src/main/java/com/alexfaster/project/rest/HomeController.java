package com.alexfaster.project.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    @RequestMapping(path = "/")
    public String hello() {
        return "hello";
    }
}
