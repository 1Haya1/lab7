package org.example.lms.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.example.lms.Api.ApiResponse;
import org.example.lms.Model.Exam;

import org.example.lms.Service.ExamService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @GetMapping("/get")
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.status(200).body(exams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exam> getExamById(@PathVariable Integer id) {
        Exam exam = examService.getExamById(id);
        if (exam != null) {
            return ResponseEntity.ok(exam);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subject/{subject}")
    public ResponseEntity getExamsBySubject(@PathVariable String subject) {
        List<Exam> examsBySubject = examService.getExamsBySubject(subject);
        if (!examsBySubject.isEmpty()) {
            return ResponseEntity.ok(examsBySubject);
        } else {
            return ResponseEntity.status(400).body("No exams found for subject: " + subject);
        }
    }

    @PostMapping("/add")
    public ResponseEntity addExam(@Valid @RequestBody Exam exam, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        examService.addExam(exam);
        return ResponseEntity.status(200).body(new ApiResponse("exam added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateExam(@PathVariable Integer id, @Valid @RequestBody Exam exam, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean isUpdated = examService.updateExam(id, exam);

        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("exam updated"));
        }
        return ResponseEntity.status(400).body("exam not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteExam(@PathVariable Integer id) {
        boolean isDeleted = examService.deleteExam(id);

        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("exam deleted"));
        }
        return ResponseEntity.status(404).body("exam not found");
    }

    @GetMapping("/search/{title}")
    public ResponseEntity<Object> searchExam(@PathVariable String title) {
        Exam exam = examService.searchExam(title);
        if (exam != null) {
            return ResponseEntity.status(200).body(exam);
        }
        return ResponseEntity.status(404).body("exam not found");
    }
}
