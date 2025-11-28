package com.telusko.question_service.controller;

import com.telusko.question_service.model.Question;
import com.telusko.question_service.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-service")
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(service.addQuestion(question));
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<Question>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getQuestionsByCategory(category));
    }
}
