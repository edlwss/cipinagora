package ru.itche.backend.service.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.entity.FullName;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPayload;
import ru.itche.backend.entity.Role;
import ru.itche.backend.entity.User;
import ru.itche.backend.repository.instructor.InstructorRepository;
import ru.itche.backend.repository.user.RoleRepository;
import ru.itche.backend.repository.user.UserRepository;
import ru.itche.backend.service.user.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefInstructorService implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final RoleRepository roleRepository;
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

        Role instructorRole = roleRepository.findByName("instructor")
                .orElseThrow(() -> new IllegalArgumentException("Роль instructor не найдена"));

        User user = userService.createUser(payload.login(),
                payload.password(),
                payload.email(),
                payload.phone(),
                instructorRole);

        Instructor instructor = new Instructor();
        instructor.setFullName(fullName);
        instructor.setPhoto(payload.photo());
        instructor.setSkillDescription(payload.skillDescription());
        instructor.setCertificateNumber(payload.certificateNumber());
        instructor.setDataVerified(payload.dataVerified());
        instructor.setOfficialEmployment(payload.officialEmployment());
        instructor.setUser(user);

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
