package ru.itche.backend.controller.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itche.backend.controller.student.payload.GetStudentPayload;
import ru.itche.backend.entity.Student;
import ru.itche.backend.controller.student.payload.NewStudentPayload;
import ru.itche.backend.service.student.StudentService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cipinagora/api/students")
public class StudentsRestController {

    private final StudentService studentService;

    @GetMapping
    public List<GetStudentPayload> getAllStudents() {
        Iterable<Student> students = studentService.getAllStudents();
        return GetStudentPayload.fromList(students);
    }

    @PostMapping("/registration")
    public ResponseEntity<Student> createStudent(@RequestBody NewStudentPayload payload,
                                                 UriComponentsBuilder uriBuilder) {

        Student created = studentService.createStudent(payload);

        return ResponseEntity.created(uriBuilder
                .replacePath("/cipinagora/api/students/{id}")
                .build(Map.of("id", created.getId())))
                .body(created);
    }
}

