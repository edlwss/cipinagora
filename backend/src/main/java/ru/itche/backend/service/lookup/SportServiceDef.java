package ru.itche.backend.service.lookup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itche.backend.entity.reference.Sport;
import ru.itche.backend.repository.lookup.SportRepository;

@Service
@RequiredArgsConstructor
public class SportServiceDef implements SportService {

    private final SportRepository sportRepository;

    @Override
    public Iterable<Sport> getSports() {
        return sportRepository.findAll();
    }
}
