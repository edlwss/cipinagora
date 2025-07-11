package ru.itche.backend.controller.student.payload;

public record NewStudentPayload(
        String login,
        String password,
        String email,
        String phone,
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String photo
) {
    public NewStudentPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}
