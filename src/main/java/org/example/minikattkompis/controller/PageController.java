package org.example.minikattkompis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping("/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/register.html")
    public String register() {
        return "register";
    }

    @GetMapping("/add-cat.html")
    public String addCat() {
        return "add-cat";
    }

    @GetMapping("/edit-cat.html")
    public String editCat() {
        return "edit-cat";
    }

    @GetMapping("/cats.html")
    public String cats() {
        return "cats";
    }

    @GetMapping("/cat-detail.html")
    public String catDetail() {
        return "cat-detail";
    }

    @GetMapping("/cat-recommendations.html")
    public String catRecommendations() {
        return "cat-recommendations";
    }

    @GetMapping("/cat-reminders.html")
    public String catReminders() {
        return "cat-reminders";
    }

    @GetMapping("/weather.html")
    public String weather() {
        return "weather";
    }
}
