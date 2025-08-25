package ru.itche.backend.controller.student.payload;

import ru.itche.backend.entity.enums.Gender;

public record UpdateStudentPayload(String fullNameLastName,
                                   String fullNameFirstName,
                                   String fullNameMiddleName,
                                   String photo,
                                   Gender gender,
                                   Long ageId) {
}
