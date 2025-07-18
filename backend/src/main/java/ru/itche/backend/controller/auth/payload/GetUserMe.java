package ru.itche.backend.controller.auth.payload;

import ru.itche.backend.entity.User;

public record GetUserMe(
        Long id,
        String role
) {
    public static GetUserMe fromUser(User user) {
        return new GetUserMe(
                user.getId(),
                user.getRole().getName()
        );
    }
}
