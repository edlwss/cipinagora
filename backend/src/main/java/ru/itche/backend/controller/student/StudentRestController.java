package ru.itche.backend.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itche.backend.controller.student.payload.GetStudentPayload;
import ru.itche.backend.entity.Student;
import ru.itche.backend.service.student.StudentService;
import ru.itche.backend.controller.student.payload.UpdateStudentPayload;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/student/{id:\\d+}")
public class StudentRestController {

    private final StudentService studentService;

    @ModelAttribute("student")
    public Student getStudent(@PathVariable("id") Long userId) {
        return this.studentService.findStudent(userId)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    @GetMapping
    public GetStudentPayload findStudent(@ModelAttribute("student") Student student) {
        return GetStudentPayload.from(student);
    }

    @PatchMapping("/edit")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,
                                           @RequestBody UpdateStudentPayload payload) {

        studentService.updateStudent(id, payload);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }
}

