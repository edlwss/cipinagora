package ru.itche.backend.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itche.backend.controller.user.payload.GetUserPayload;
import ru.itche.backend.controller.user.payload.UpdateUserPasswordPayload;
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

    @PatchMapping("/edit/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable("id") Long id,
                                                @RequestBody UpdateUserPasswordPayload payload) {
        userService.updateUserPassword(id, payload);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public GetUserPayload getUser(@PathVariable("id") Long id) {
        return GetUserPayload.from(userService.findById(id));
    }
}
