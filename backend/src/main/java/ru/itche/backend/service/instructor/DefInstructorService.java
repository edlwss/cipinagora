package ru.itche.backend.service.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.entity.FullName;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPayload;
import ru.itche.backend.repository.instructor.InstructorRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefInstructorService implements InstructorService {

    private final InstructorRepository instructorRepository;

    @Override
    public Iterable<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return instructorRepository.findById(id);
    }

    @Override
    @Transactional
    public Instructor create(NewInstructorPayload payload) {
        FullName fullName = new FullName(
                payload.fullNameFirstName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );

        Instructor instructor = new Instructor(
                null,
                fullName,
                payload.photo(),
                payload.skillDescription(),
                payload.certificateNumber(),
                payload.dataVerified(),
                payload.officialEmployment()
        );

        return instructorRepository.save(instructor);
    }

    @Override
    @Transactional
    public void update(Long id, UpdateInstructorPayload payload) {
        instructorRepository.findById(id)
                .ifPresent(instructor -> {
                    instructor.setFullName(new FullName(
                            payload.fullNameFirstName(),
                            payload.fullNameFirstName(),
                            payload.fullNameMiddleName()
                    ));
                    instructor.setPhoto(payload.photo());
                    instructor.setSkillDescription(payload.skillDescription());
                    instructor.setCertificateNumber(payload.certificateNumber());
                    instructor.setDataVerified(payload.dataVerified());
                    instructor.setOfficialEmployment(payload.officialEmployment());
                });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }
}
