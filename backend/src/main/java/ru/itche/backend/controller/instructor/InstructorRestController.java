package ru.itche.backend.controller.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.service.instructor.InstructorService;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/instructors/instructor/{id:\\d+}")
public class InstructorRestController {

    private final InstructorService instructorService;

    @ModelAttribute("instructor")
    public Instructor getInstructor(@PathVariable("id") Long id) {
        return instructorService.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    @GetMapping
    public Instructor get(@ModelAttribute("instructor") Instructor instructor) {
        return instructor;
    }

    @PatchMapping
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody UpdateInstructorPayload payload) {
        instructorService.update(id, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
