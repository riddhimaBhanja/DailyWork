package com.telusko.quiz_service.dto;

import java.util.List;

public class QuizSubmissionRequest {

 
    private List<QuizAnswer> answers;

    public QuizSubmissionRequest() {}

    public List<QuizAnswer> getAnswers() { return answers; }
    public void setAnswers(List<QuizAnswer> answers) { this.answers = answers; }
}
