package com.telusko.quiz_service.service;

import com.telusko.quiz_service.dto.*;
import com.telusko.quiz_service.exception.ResourceNotFoundException;
import com.telusko.quiz_service.model.Question;
import com.telusko.quiz_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class QuizEvaluationService {

    private final QuestionRepository repo;

    public QuizEvaluationService(QuestionRepository repo) {
        this.repo = repo;
    }

    public QuizResultResponse evaluateQuiz(QuizSubmissionRequest submission) {

        List<QuizAnswer> answers = submission.getAnswers();

        List<String> ids = answers.stream()
                .map(QuizAnswer::getQuestionId)
                .collect(Collectors.toList());

        List<Question> questions = repo.findAllById(ids);

        Map<String, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int correct = 0;
        int totalObtained = 0;
        int totalPossible = 0;

        for (QuizAnswer ans : answers) {
            Question q = questionMap.get(ans.getQuestionId());

            if (q == null)
                throw new ResourceNotFoundException("Question not found: " + ans.getQuestionId());

            totalPossible += q.getMarks();

            if (ans.getSelectedOptionIndex() != null &&
                ans.getSelectedOptionIndex() == q.getCorrectOptionIndex()) {

                correct++;
                totalObtained += q.getMarks();
            }
        }

        return new QuizResultResponse(
                answers.size(),
                correct,
                totalObtained,
                totalPossible
        );
    }
}
