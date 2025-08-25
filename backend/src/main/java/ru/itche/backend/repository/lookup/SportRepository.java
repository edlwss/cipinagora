package ru.itche.backend.repository.lookup;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.reference.Sport;

public interface SportRepository extends CrudRepository<Sport, Long> {
}
