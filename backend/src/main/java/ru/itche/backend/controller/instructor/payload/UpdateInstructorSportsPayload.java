package ru.itche.backend.controller.instructor.payload;

import java.util.List;

public record UpdateInstructorSportsPayload(
        List<Long> sportIds
) {
}