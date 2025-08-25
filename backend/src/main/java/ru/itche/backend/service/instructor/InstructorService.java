package ru.itche.backend.service.instructor;

import ru.itche.backend.controller.instructor.payload.UpdateInstructorSportsPayload;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPersonalPayload;

import java.util.Optional;

public interface InstructorService {

    Iterable<Instructor> getAll();
    Optional<Instructor> findById(Long id);
    Instructor create(NewInstructorPayload payload);
    void updatePersonal(Long id, UpdateInstructorPersonalPayload payload);
    void addSports(Long id, UpdateInstructorSportsPayload payload);
    void delete(Long id);
}
