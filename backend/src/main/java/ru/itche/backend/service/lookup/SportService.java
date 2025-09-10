package ru.itche.backend.service.lookup;
import ru.itche.backend.entity.reference.Sport;

public interface SportService {
    Iterable<Sport> getSports();
}
