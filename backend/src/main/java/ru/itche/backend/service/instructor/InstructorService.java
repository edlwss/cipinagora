package ru.itche.backend.service.instructor;

import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPayload;

import java.util.Optional;

public interface InstructorService {

    Iterable<Instructor> getAll();
    Optional<Instructor> findById(Long id);
    Instructor create(NewInstructorPayload payload);
    void update(Long id, UpdateInstructorPayload payload);
    void delete(Long id);
}
