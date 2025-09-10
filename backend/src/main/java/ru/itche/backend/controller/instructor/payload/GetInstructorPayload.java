package ru.itche.backend.controller.instructor.payload;

import ru.itche.backend.controller.lookup.payload.SportsPayload;
import ru.itche.backend.entity.Instructor;

import java.util.ArrayList;
import java.util.List;

public record GetInstructorPayload(
        String fullNameLastName,
        String fullNameFirstName,
        String fullNameMiddleName,
        String fullName,
        String gender,
        Long ageId,
        String age,
        String photo,

        String skillDescription,
        String certificateNumber,
        boolean dataVerified,
        boolean officialEmployment,
        List<SportsPayload> sports,
        Long user_id
) {

    public static GetInstructorPayload from(Instructor instructor) {
        List<SportsPayload> sportPayloads = instructor.getSports().stream()
                .map(s -> new SportsPayload(s.getId(), s.getName()))
                .toList();

        return new GetInstructorPayload(
                instructor.getFullName().getLastName(),
                instructor.getFullName().getFirstName(),
                instructor.getFullName().getMiddleName(),
                instructor.getFullName().getFullName(),
                instructor.getGender().name(),
                instructor.getAge().getId(),
                instructor.getAge().getNameCategories(),
                instructor.getPhoto(),

                instructor.getSkillDescription(),
                instructor.getCertificateNumber(),
                instructor.isDataVerified(),
                instructor.isOfficialEmployment(),
                sportPayloads,
                instructor.getUser().getId()
        );
    }

    public static List<GetInstructorPayload> fromList(Iterable<Instructor> instructors) {
        List<GetInstructorPayload> list = new ArrayList<>();
        for (Instructor instructor : instructors) {
            list.add(from(instructor));
        }
        return list;
    }

}
