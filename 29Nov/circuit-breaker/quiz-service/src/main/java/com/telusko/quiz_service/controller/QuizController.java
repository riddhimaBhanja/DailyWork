package com.telusko.quiz_service.controller;

import com.telusko.quiz_service.dto.QuizResultResponse;
import com.telusko.quiz_service.dto.QuizSubmissionRequest;
import com.telusko.quiz_service.service.QuizEvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz-service")
public class QuizController {

    private final QuizEvaluationService service;

    public QuizController(QuizEvaluationService service) {
        this.service = service;
    }

    @PostMapping("/submit")
    public ResponseEntity<QuizResultResponse> submitQuiz(
            @Valid @RequestBody QuizSubmissionRequest submission) {

        QuizResultResponse result = service.evaluateQuiz(submission);
        return ResponseEntity.ok(result);
    }
}
