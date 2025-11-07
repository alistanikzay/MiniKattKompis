package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.repository.CatRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable("cats")
    public List<Cat> getAllCats() {
        System.out.println("Fetching cats from DB...");
        return repo.findAll();
    }

    @CacheEvict(value = "cats", allEntries = true)
    public Cat addCat(Cat cat) {
        cat.setName(nameService.safeCatName(cat.getName()));
        return repo.save(cat);
    }

    @Cacheable(value = "cats", key = "#id")
    public Cat getCat(Long id) {
        System.out.println("Fetching cat " + id + " from DB...");
        return repo.findById(id).orElse(null);
    }
}
