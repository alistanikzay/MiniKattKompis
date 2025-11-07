package org.example.minikattkompis.controller;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.service.CatService;
import org.jspecify.annotations.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cats")
public class CatRestController {

    private final CatService catService;

    public CatRestController(@NonNull CatService catService) {
        this.catService = catService;
    }

    // --------------------
    // Version 1: Hämta alla katter
    // --------------------
    @GetMapping(value = "/all", headers = "Version=1")
    public @NonNull List<Cat> getAllCatsV1() {
        return catService.getAllCats();
    }

    // --------------------
    // Version 2: Hämta alla katter
    // --------------------
    @GetMapping(value = "/all", headers = "Version=2")
    public @NonNull List<Cat> getAllCatsV2() {
        return catService.getAllCats();
    }

    // --------------------
    // Version 1: Lägg till katt
    // --------------------
    @PostMapping(value = "/add", headers = "Version=1")
    public @NonNull Cat addCatV1(@RequestBody @NonNull Cat cat) {
        return catService.addCat(cat);
    }

    // --------------------
    // Version 2: Lägg till katt
    // --------------------
    @PostMapping(value = "/add", headers = "Version=2")
    public @NonNull Cat addCatV2(@RequestBody @NonNull Cat cat) {
        return catService.addCat(cat);
    }
}