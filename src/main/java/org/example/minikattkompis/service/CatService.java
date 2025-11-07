package org.example.minikattkompis.service;

import org.example.minikattkompis.model.Cat;
import org.example.minikattkompis.repository.CatRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CatService {

    private final CatRepository repo;
    private final CatNameService nameService;

    public CatService(CatRepository repo, CatNameService nameService) {
        this.repo = repo;
        this.nameService = nameService;
    }

    // --------------------
    // Alla katter
    // --------------------
    @Cacheable("cats")
    public List<Cat> getAllCats() {
        System.out.println("Fetching cats from DB...");
        return repo.findAll();
    }

    // --------------------
    // Lägg till katt
    // --------------------
    @CacheEvict(value = "cats", allEntries = true)
    public Cat addCat(Cat cat) {
        cat.setName(nameService.safeCatName(cat.getName()));
        return repo.save(cat);
    }

    // --------------------
    // Hämta en specifik katt
    // --------------------
    @Cacheable(value = "cats", key = "#id")
    public Cat getCat(Long id) {
        System.out.println("Fetching cat " + id + " from DB...");
        return repo.findById(id).orElse(null);
    }

    // --------------------
    // Påminnelser om veterinärbesök
    // --------------------
    @Cacheable("upcomingVetVisits")
    public List<Cat> getUpcomingVetVisits() {
        System.out.println("Fetching upcoming vet visits from DB...");
        return repo.findAll().stream()
                .filter(c -> c.getNextVetVisit() != null &&
                        c.getNextVetVisit().isBefore(LocalDate.now().plusDays(7)))
                .collect(Collectors.toList());
    }
}
