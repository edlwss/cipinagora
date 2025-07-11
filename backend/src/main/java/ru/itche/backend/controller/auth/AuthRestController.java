package ru.itche.backend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.controller.auth.payload.JwtRequest;
import ru.itche.backend.service.user.AuthService;


@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/cipinagora/api/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }
}