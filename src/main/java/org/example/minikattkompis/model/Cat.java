package org.example.minikattkompis.model;

import jakarta.persistence.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@Entity
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private @NonNull String name;
    private int age;
    private @Nullable String favoriteToy;


    protected Cat() {}


    public Cat(Long id, @NonNull String name, int age, @Nullable String favoriteToy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.favoriteToy = favoriteToy;
    }


    public Cat(@NonNull String name, int age, @Nullable String favoriteToy) {
        this.name = name;
        this.age = age;
        this.favoriteToy = favoriteToy;
    }


    public Long getId() { return id; }
    public @NonNull String getName() { return name; }
    public int getAge() { return age; }
    public @Nullable String getFavoriteToy() { return favoriteToy; }

    public void setName(@NonNull String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setFavoriteToy(@Nullable String favoriteToy) { this.favoriteToy = favoriteToy; }

    // --------------------
// Veterinärbesök
// --------------------
    private java.time.LocalDate nextVetVisit;

    public java.time.LocalDate getNextVetVisit() {
        return nextVetVisit;
    }

    public void setNextVetVisit(java.time.LocalDate nextVetVisit) {
        this.nextVetVisit = nextVetVisit;
    }
}
