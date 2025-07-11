package ru.itche.backend.controller.auth.payload;

public record JwtRequest (
        String login,
        String password ){
}
