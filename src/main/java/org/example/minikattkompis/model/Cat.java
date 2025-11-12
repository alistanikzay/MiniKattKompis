package org.example.minikattkompis.model;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

@Entity
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NonNull String name;
    private int age;
    private @Nullable String favoriteToy;

    // --------------------
    // Veterinärbesök
    // --------------------
    private @Nullable LocalDate nextVetVisit;

    // JPA kräver en no-args konstruktor
    public Cat() {}

    // Full konstruktor
    public Cat(Long id, @NonNull String name, int age, @Nullable String favoriteToy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteToy = favoriteToy;
    }

    // Konstruktor utan id (för nya objekt)
    public Cat(@NonNull String name, int age, @Nullable String favoriteToy) {
        this.name = name;
        this.age = age;
        this.favoriteToy = favoriteToy;
    }

    // --------------------
    // Getters
    // --------------------
    public Long getId() { return id; }
    public @NonNull String getName() { return name; }
    public int getAge() { return age; }
    public @Nullable String getFavoriteToy() { return favoriteToy; }
    public @Nullable LocalDate getNextVetVisit() { return nextVetVisit; }

    // --------------------
    // Setters
    // --------------------
    public void setName(@NonNull String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setFavoriteToy(@Nullable String favoriteToy) { this.favoriteToy = favoriteToy; }
    public void setNextVetVisit(@Nullable LocalDate nextVetVisit) { this.nextVetVisit = nextVetVisit; }
}
