package ru.itche.backend.service.lookup;

import ru.itche.backend.entity.reference.AgeCategories;

import java.util.Optional;

public interface AgeService {

    Iterable<AgeCategories> getAges();
    Optional<AgeCategories> setAge(Long id);
}
