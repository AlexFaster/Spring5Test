package com.alexfaster.project.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Auth operations"}, description = " ")
@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/v1/register")
    public String register() {
        return "forget password";
    }

}
