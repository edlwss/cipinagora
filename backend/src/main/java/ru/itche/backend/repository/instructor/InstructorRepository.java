package ru.itche.backend.repository.instructor;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.Instructor;

public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
