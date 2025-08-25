package ru.itche.backend.controller.instructor.payload;

import ru.itche.backend.entity.enums.Gender;

import java.util.List;

public record NewInstructorPayload(
        String login,
        String password,
        String email,
        String phone,

        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String photo,
        Gender gender,
        Long ageId,
        List<Long> sportIds,

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

