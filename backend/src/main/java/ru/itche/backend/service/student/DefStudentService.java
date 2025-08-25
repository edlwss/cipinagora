package ru.itche.backend.service.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itche.backend.controller.student.payload.NewStudentPayload;
import ru.itche.backend.controller.student.payload.UpdateStudentPayload;
import ru.itche.backend.entity.reference.AgeCategories;
import ru.itche.backend.entity.auth.Role;
import ru.itche.backend.entity.Student;
import ru.itche.backend.entity.valueobject.FullName;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.repository.lookup.AgeRepository;
import ru.itche.backend.repository.student.StudentRepository;
import ru.itche.backend.repository.user.RoleRepository;
import ru.itche.backend.service.user.UserService;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefStudentService implements StudentService {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final AgeRepository ageRepository;
    private final UserService userService;

    @Override
    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student createStudent(NewStudentPayload payload) {
        FullName fullName = new FullName(
                payload.fullNameLastName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );

        AgeCategories age = ageRepository.findById(payload.ageId())
                .orElseThrow(() -> new IllegalArgumentException("Возрастная категория не найдена"));

        Role studentRole = roleRepository.findByName("student")
                .orElseThrow(() -> new IllegalArgumentException("Роль student не найдена"));


        User user = userService.createUser(payload.login(),
                payload.password(),
                payload.email(),
                payload.phone(),
                studentRole);

        Student student = new Student();
        student.setFullName(fullName);
        student.setPhoto(payload.photo());
        student.setAge(age);
        student.setGender(payload.gender());
        student.setUser(user);

        return studentRepository.save(student);
    }


    @Override
    public Optional<Student> findStudent(Long studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    @Transactional
    public void updateStudent(Long id, UpdateStudentPayload payload) {
        FullName fullName = new FullName(
                payload.fullNameLastName(),
                payload.fullNameFirstName(),
                payload.fullNameMiddleName()
        );
        studentRepository.findById(id)
                .ifPresent(student -> {
                    student.setFullName(fullName);
                    student.setAge(ageRepository.findById(payload.ageId())
                            .orElseThrow(() -> new IllegalArgumentException
                                    ("Возрастная категория не найдена")));
                    student.setGender(payload.gender());
                    student.setPhoto(payload.photo());
                });
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        userService.deleteUser(id);
        studentRepository.deleteById(id);
    }
}
