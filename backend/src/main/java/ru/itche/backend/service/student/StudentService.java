package ru.itche.backend.service.student;

import ru.itche.backend.controller.student.payload.NewStudentPayload;
import ru.itche.backend.controller.student.payload.UpdateStudentPayload;
import ru.itche.backend.entity.Student;

import java.util.Optional;

public interface StudentService {
    Iterable<Student> getAllStudents();

    Student createStudent(NewStudentPayload payload);

    Optional<Student> findStudent(Long studentId);

    void updateStudent(Long studentId, UpdateStudentPayload payload);

    void deleteStudent(Long studentId);
}
