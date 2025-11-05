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


    @GetMapping(value = "/all", headers = "Version=2")
    public List<Cat> getAllCatsV2() {
        return catService.getAllCats();
    }


    @PostMapping(value = "/add", headers = "Version=2")
    public Cat addCatV2(@RequestBody Cat cat) {
        return catService.addCat(cat);
    }
}
