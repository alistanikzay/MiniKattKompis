package org.example.minikattkompis.dto;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class CatDTO {
    private Long id;
    private @NonNull String name;
    private int age;
    private @Nullable String favoriteToy;

    public CatDTO() {}

    public CatDTO(Long id, @NonNull String name, int age, @Nullable String favoriteToy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteToy = favoriteToy;
    }

    public Long getId() { return id; }
    public @NonNull String getName() { return name; }
    public int getAge() { return age; }
    public @Nullable String getFavoriteToy() { return favoriteToy; }

    public void setId(Long id) { this.id = id; }
    public void setName(@NonNull String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setFavoriteToy(@Nullable String favoriteToy) { this.favoriteToy = favoriteToy; }
}
