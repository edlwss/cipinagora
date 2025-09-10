package ru.itche.backend.repository.document;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itche.backend.entity.Document;

import java.util.List;


@Repository
public interface DocRepository extends CrudRepository<Document, Long> {

    @Query("SELECT d " +
            "FROM Document d " +
            "WHERE d.instructor.id = :instructorId")
    List<Document> findAllByInstructorId(@Param("instructorId") Long instructorId);
}
