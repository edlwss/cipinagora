package ru.itche.backend.controller.student.payload;

import ru.itche.backend.entity.enums.Gender;

public record NewStudentPayload(
        String login,
        String password,
        String email,
        String phone,
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        Gender gender,
        Long ageId,
        String photo
) {
    public NewStudentPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}
