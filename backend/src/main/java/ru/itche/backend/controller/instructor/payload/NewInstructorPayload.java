package ru.itche.backend.controller.instructor.payload;

public record NewInstructorPayload(
        String login,
        String password,
        String email,
        String phone,

        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String photo,

        String skillDescription,
        String certificateNumber,
        boolean dataVerified,
        boolean officialEmployment
) {
    public NewInstructorPayload {
        if (photo != null && photo.isBlank()) {
            photo = null;
        }
    }
}

