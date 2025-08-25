package ru.itche.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.controller.user.payload.UpdateUserPayload;
import ru.itche.backend.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/user/{id:\\d+}")
public class UserController {
    private final UserService userService;

    @PatchMapping("/edit")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                        @RequestBody UpdateUserPayload payload) {
        userService.updateUser(id, payload);
        return ResponseEntity.noContent().build();
    }
}
