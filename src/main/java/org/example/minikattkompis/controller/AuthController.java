package org.example.minikattkompis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // pekar på templates/login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // pekar på templates/register.html
    }
}
