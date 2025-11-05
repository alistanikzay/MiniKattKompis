package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CatController {

    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
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
        model.addAttribute("cat", new Cat());
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
}
