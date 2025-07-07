package ru.itche.backend.controller.center.payload;

public record NewSportsCenterPayload(String name,
                                     String description,
                                     String coordinates,
                                     String addressRegion,
                                     String addressCity,
                                     String addressStreet,
                                     String addressBuilding) {
}
