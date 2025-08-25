package ru.itche.backend.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.reference.AgeCategories;

public interface AgeRepository extends CrudRepository<AgeCategories, Long> {
}
