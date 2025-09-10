package ru.itche.backend.controller.user.payload;

public record UpdateUserPayload(
        String login,
        String email,
        String phone
) {
}
