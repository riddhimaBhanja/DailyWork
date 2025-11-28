package com.telusko.quiz_service.dto;

public class QuizAnswer {

    private String questionId;
    private Integer selectedOptionIndex;

    public QuizAnswer() {}

    public String getQuestionId() { return questionId; }
    public void setQuestionId(String questionId) { this.questionId = questionId; }

    public Integer getSelectedOptionIndex() { return selectedOptionIndex; }
    public void setSelectedOptionIndex(Integer selectedOptionIndex) { this.selectedOptionIndex = selectedOptionIndex; }
}
