package ru.itche.backend.controller.user.payload;

public record UpdateUserPasswordPayload(
        String oldPassword,
        String newPassword
) {
}
