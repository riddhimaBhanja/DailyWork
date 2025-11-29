package com.telusko.question_service.service;

import com.telusko.question_service.model.Question;
import com.telusko.question_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository repo;

    public QuestionService(QuestionRepository repo) {
        this.repo = repo;
    }

    public Question addQuestion(Question q) {
        return repo.save(q);
    }

    public List<Question> getQuestionsByCategory(String category) {
        return repo.findByCategory(category);
    }
}
