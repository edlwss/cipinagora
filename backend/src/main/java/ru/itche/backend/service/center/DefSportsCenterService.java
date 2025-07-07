package ru.itche.backend.service.center;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.controller.center.payload.NewSportsCenterPayload;
import ru.itche.backend.controller.center.payload.UpdateSportsCenterPayload;
import ru.itche.backend.entity.Address;
import ru.itche.backend.entity.SportsCenter;
import ru.itche.backend.repository.center.SportsCenterRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefSportsCenterService implements SportsCenterService {

    private final SportsCenterRepository centerRepository;

    @Override
    public Iterable<SportsCenter> getAll() {
        return centerRepository.findAll();
    }

    @Override
    public Optional<SportsCenter> findById(Long id) {
        return centerRepository.findById(id);
    }

    @Override
    @Transactional
    public SportsCenter create(NewSportsCenterPayload payload) {
        Address address = new Address(
                payload.addressRegion(),
                payload.addressCity(),
                payload.addressStreet(),
                payload.addressBuilding()
        );

        SportsCenter center = new SportsCenter(
                null,
                payload.name(),
                payload.description(),
                address,
                payload.coordinates()
        );

        return centerRepository.save(center);
    }

    @Override
    @Transactional
    public void update(Long id, UpdateSportsCenterPayload payload) {
        Address address = new Address(
                payload.addressRegion(),
                payload.addressCity(),
                payload.addressStreet(),
                payload.addressBuilding()
        );

        centerRepository.findById(id)
                .ifPresent(center -> {
                    center.setName(payload.name());
                    center.setDescription(payload.description());
                    center.setCoordinates(payload.coordinates());
                    center.setAddress(address);
                });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        centerRepository.deleteById(id);
    }
}
