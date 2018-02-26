package com.alexfaster.project.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @PostMapping("/v1/login")
    public String login() {
        return "login";
    }

    @PostMapping("/v1/forgetPassword")
    public String forgetPassword() {
        return "forget password";
    }

    @PostMapping("v1/logout")
    public String logout() {
        return "logout";
    }
}
