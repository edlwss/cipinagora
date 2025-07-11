package ru.itche.backend.controller.student.payload;

import ru.itche.backend.entity.Student;

import java.util.ArrayList;
import java.util.List;

public record GetStudentPayload(
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String photo,
        Long user_id
) {
    public static GetStudentPayload from(Student student) {
        return new GetStudentPayload(
                student.getFullName().getLastName(),
                student.getFullName().getFirstName(),
                student.getFullName().getMiddleName(),
                student.getPhoto(),
                student.getUser().getId()
        );
    }

    public static List<GetStudentPayload> fromList(Iterable<Student> students) {
        List<GetStudentPayload> list = new ArrayList<>();
        for (Student student : students) {
            list.add(from(student));
        }
        return list;
    }
}
