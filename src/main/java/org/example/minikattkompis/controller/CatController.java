package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.example.minikattkompis.service.CatRecommendationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CatController {

    private final CatService catService;
    private final CatRecommendationService recommendationService;

    // Konstruktor
    public CatController(CatService catService, CatRecommendationService recommendationService) {
        this.catService = catService;
        this.recommendationService = recommendationService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/cats")
    public String listCats(Model model) {
        model.addAttribute("cats", catService.getAllCats());
        return "cats";
    }

    @GetMapping("/cats/add")
    public String addCatForm(Model model) {
        model.addAttribute("cat", new Cat("", 0, null));
        return "add-cat";
    }

    @PostMapping("/cats/add")
    public String saveCat(@ModelAttribute Cat cat) {
        catService.addCat(cat);
        return "redirect:/cats";
    }

    @GetMapping("/cats/{id}")
    public String catDetail(@PathVariable Long id, Model model) {
        model.addAttribute("cat", catService.getCat(id));
        return "cat-detail";
    }

    // --------------------
    // Rekommendationer
    // --------------------
    @GetMapping("/cats/{id}/recommendations")
    public String catRecommendations(@PathVariable Long id, Model model) {
        Cat cat = catService.getCat(id);
        model.addAttribute("cat", cat);
        model.addAttribute("recommendations", recommendationService.getRecommendations(cat));
        model.addAttribute("catId", id);
        return "cat-recommendations";
    }

    // --------------------
    // Veterinärpåminnelser
    // --------------------
    @GetMapping("/cats/reminders")
    public String catReminders(Model model) {
        model.addAttribute("upcomingVetVisits", catService.getUpcomingVetVisits());
        return "cat-reminders";
    }
}
