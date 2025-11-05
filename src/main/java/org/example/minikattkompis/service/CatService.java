package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.repository.CatRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CatService {

    private final CatRepository repo;
    private final CatNameService nameService;

    public CatService(CatRepository repo, CatNameService nameService) {
        this.repo = repo;
        this.nameService = nameService;
    }

    public List<Cat> getAllCats() {
        return repo.findAll();
    }

    public Cat addCat(Cat cat) {
        cat.setName(nameService.safeCatName(cat.getName()));
        return repo.save(cat);
    }

    public Cat getCat(Long id) {
        return repo.findById(id).orElse(null); }
    }