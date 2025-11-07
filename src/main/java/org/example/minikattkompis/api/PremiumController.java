package org.example.minikattkompis.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    // Endast PREMIUM kan anropa
    @GetMapping("/recommendations")
    @PreAuthorize("hasRole('PREMIUM')")
    public List<String> getRecommendations() {
        return List.of(
                "Premium-kattleksak 1",
                "Premium-kattleksak 2",
                "Premium-kattleksak 3"
        );
    }
}
