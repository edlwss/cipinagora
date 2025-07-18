package ru.itche.backend.controller.instructor;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.controller.instructor.payload.GetInstructorPayload;
import ru.itche.backend.entity.Instructor;
import ru.itche.backend.service.instructor.InstructorService;
import ru.itche.backend.controller.instructor.payload.UpdateInstructorPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/instructor/{id:\\d+}")
public class InstructorRestController {

    private final InstructorService instructorService;

    @ModelAttribute("instructor")
    public Instructor getInstructor(@PathVariable("id") Long userId) {
        return instructorService.findById(userId)
                .orElseThrow(() -> new InstructorRestController.InstructorNotFoundException(userId));
    }

    @GetMapping
    public  GetInstructorPayload get(@ModelAttribute("instructor") Instructor instructor) {
        return GetInstructorPayload.from(instructor);
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                    @RequestBody UpdateInstructorPayload payload) {
        instructorService.update(id, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private static class InstructorNotFoundException extends RuntimeException {
        public InstructorNotFoundException(Long id) {
            super("Student with id " + id + " not found");
        }
    }
}
