package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
public class CatRestController {

    private final CatService catService;

    public CatRestController(CatService catService) {
        this.catService = catService;
    }

    @RequestMapping(value = "/all", version = "2")
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, version = "2")
    public Cat addCat(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }
}
