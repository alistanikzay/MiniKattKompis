package org.example.minikattkompis.service;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class CatNameService {
    public @NonNull String safeCatName(@Nullable String name) {
        if (name == null || name.isBlank()) {
            return "Okänd katt";
        }
        return name;
    }
}
