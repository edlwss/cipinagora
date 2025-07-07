package ru.itche.backend.controller.student.payload;

public record UpdateStudentPayload(String fullNameLastName,
                                   String fullNameFirstName,
                                   String fullNameMiddleName,
                                   String photo) {
    public UpdateStudentPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}
