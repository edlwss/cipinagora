package ru.itche.backend.service.lookup;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.repository.lookup.AgeRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DefAgeService implements AgeService{

    private final AgeRepository ageRepository;

    @Override
    public Iterable<AgeCategories> getAges() {
        return ageRepository.findAll();
    }

    @Override
    public Optional<AgeCategories> setAge(Long id) {
        return ageRepository.findById(id);
    }
}
