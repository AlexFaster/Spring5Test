package com.alexfaster.project.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(tags = {"Auth operations"}, description = " ")
@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/v1/forgetPassword")
    public String forgetPassword() {
        return "forget password";
    }

}
