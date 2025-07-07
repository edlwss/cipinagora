package ru.itche.backend.repository.student;

import org.springframework.data.repository.CrudRepository;
import ru.itche.backend.entity.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
