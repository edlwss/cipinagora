package ru.itche.backend.controller.center.payload;

public record NewSportsCenterPayload(
        String login,
        String password,
        String email,
        String phone,

        String name,
        String description,
        String coordinates,
        String addressRegion,
        String addressCity,
        String addressStreet,
        String addressBuilding) {
}
