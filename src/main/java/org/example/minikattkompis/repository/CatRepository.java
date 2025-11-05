package org.example.minikattkompis.repository;

import org.example.minikattkompis.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
