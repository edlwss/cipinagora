package ru.itche.backend.controller.instructor.payload;

import ru.itche.backend.entity.enums.Gender;

public record UpdateInstructorPersonalPayload(
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        Gender gender,
        Long ageId,
        String skillDescription
) {
}
