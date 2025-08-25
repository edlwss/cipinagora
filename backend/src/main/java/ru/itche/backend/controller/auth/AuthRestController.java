package ru.itche.backend.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.controller.auth.payload.GetUserMe;
import ru.itche.backend.controller.auth.payload.JwtRequest;
import ru.itche.backend.entity.auth.User;
import ru.itche.backend.service.user.AuthService;
import ru.itche.backend.service.user.UserService;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
public class AuthRestController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/cipinagora/api/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @GetMapping("/cipinagora/api/auth/me")
    public ResponseEntity<GetUserMe> getCurrentUserInfo(Principal principal) {
        User user = userService.findByLogin(principal.getName());
        return ResponseEntity.ok(GetUserMe.fromUser(user));
    }
}