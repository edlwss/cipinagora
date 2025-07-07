package ru.itche.backend.controller.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.controller.instructor.payload.NewInstructorPayload;
import ru.itche.backend.service.instructor.InstructorService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/instructors")
public class InstructorsRestController {

    private final InstructorService instructorService;

    @GetMapping
    public Iterable<Instructor> getAll() {
        return instructorService.getAll();
    }

    @PostMapping
    public ResponseEntity<Instructor> create(@RequestBody NewInstructorPayload payload,
                                             UriComponentsBuilder uriBuilder) {

        Instructor created = instructorService.create(payload);

        return ResponseEntity.created(uriBuilder
                        .replacePath("/cipinagora/api/instructors/{id}")
                        .build(Map.of("id", created.getId())))
                .body(created);
    }
}
