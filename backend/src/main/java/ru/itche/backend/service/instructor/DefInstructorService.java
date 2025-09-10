package ru.itche.backend.service.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPhotoPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorSportsPayload;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPersonalPayload;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.reference.Sport;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.instructor.InstructorRepository;
import ru.itche.backend.repository.lookup.AgeRepository;
import ru.itche.backend.repository.lookup.SportRepository;
import ru.itche.backend.repository.user.RoleRepository;
import ru.itche.backend.service.user.UserService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DefInstructorService implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
    private final AgeRepository ageRepository;
    private final SportRepository sportRepository;
    private final UserService userService;

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
                payload.fullNameLastName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );

        AgeCategories age = ageRepository.findById(payload.ageId())
                .orElseThrow(() -> new IllegalArgumentException("Возрастная категория не найдена"));

        Role instructorRole = roleRepository.findByName("instructor")
                .orElseThrow(() -> new IllegalArgumentException("Роль instructor не найдена"));

        User user = userService.createUser(payload.login(),
                payload.password(),
                payload.email(),
                payload.phone(),
                instructorRole);

        Set<Sport> sports = StreamSupport.stream(
                sportRepository.findAllById(payload.sportIds()).spliterator(), false)
                .collect(Collectors.toSet());

        Instructor instructor = new Instructor();
        instructor.setFullName(fullName);
        instructor.setAge(age);
        instructor.setGender(payload.gender());
        instructor.setPhoto(payload.photo());
        instructor.setSkillDescription(payload.skillDescription());
        instructor.setCertificateNumber(payload.certificateNumber());
        instructor.setDataVerified(payload.dataVerified());
        instructor.setOfficialEmployment(payload.officialEmployment());
        instructor.setSports(sports);
        instructor.setUser(user);

        return instructorRepository.save(instructor);
    }

    @Override
    @Transactional
    public void updatePersonal(Long id, UpdateInstructorPersonalPayload payload) {
        instructorRepository.findById(id)
                .ifPresent(instructor -> {

                    Optional.ofNullable(payload.gender())
                            .ifPresent(instructor::setGender);

                    Optional.ofNullable(payload.ageId())
                            .ifPresent(ageId -> instructor.setAge(
                                    ageRepository.findById(ageId)
                                            .orElseThrow(() -> new IllegalArgumentException("Возрастная категория не найдена"))
                            ));

                    Optional.ofNullable(payload.skillDescription())
                            .ifPresent(instructor::setSkillDescription);

                    if (payload.fullNameLastName() != null ||
                            payload.fullNameFirstName() != null ||
                            payload.fullNameMiddleName() != null) {
                        instructor.setFullName(new FullName(
                                Optional.ofNullable(payload.fullNameLastName()).orElse(instructor.getFullName().getLastName()),
                                Optional.ofNullable(payload.fullNameFirstName()).orElse(instructor.getFullName().getFirstName()),
                                Optional.ofNullable(payload.fullNameMiddleName()).orElse(instructor.getFullName().getMiddleName())
                        ));
                    }
                });
    }

    @Override
    @Transactional
    public void addSports(Long id, UpdateInstructorSportsPayload payload) {

        Set<Sport> sports = StreamSupport.stream(
                        sportRepository.findAllById(payload.sportIds()).spliterator(), false)
                .collect(Collectors.toSet());

        instructorRepository.findById(id)
                .ifPresent(instructor -> {
                    instructor.setSports(sports);
                });

    }

    @Override
    @Transactional
    public void updatePhoto(Long id, UpdateInstructorPhotoPayload payload) {
        instructorRepository.findById(id)
                .ifPresent(instructor -> {
                    instructor.setPhoto(payload.photo());
                });
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userService.deleteUser(id);
        instructorRepository.deleteById(id);
    }
}
