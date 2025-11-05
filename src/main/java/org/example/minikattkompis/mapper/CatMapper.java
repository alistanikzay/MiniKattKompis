package org.example.minikattkompis.mapper;

import org.example.minikattkompis.dto.CatDTO;
import org.example.minikattkompis.model.Cat;
import org.springframework.stereotype.Component;

@Component
public class CatMapper {

    public CatDTO toDTO(Cat cat) {
        if (cat == null) return null;
        return new CatDTO(cat.getId(), cat.getName(), cat.getAge(), cat.getFavoriteToy());
    }

    public Cat toEntity(CatDTO dto) {
        if (dto == null) return null;
        Cat cat = new Cat();
        cat.setName(dto.getName());
        cat.setAge(dto.getAge());
        cat.setFavoriteToy(dto.getFavoriteToy());
        return cat;
    }
}
