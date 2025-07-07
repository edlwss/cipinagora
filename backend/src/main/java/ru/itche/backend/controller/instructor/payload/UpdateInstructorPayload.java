package ru.itche.backend.controller.instructor.payload;

public record UpdateInstructorPayload(String fullNameLastName,
                                      String fullNameFirstName,
                                      String fullNameMiddleName,
                                      String photo,
                                      String skillDescription,
                                      String certificateNumber,
                                      boolean dataVerified,
                                      boolean officialEmployment) {
}
