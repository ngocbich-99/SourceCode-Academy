package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.model.request.question.CreateQuestionRequest;
import com.example.demo.model.request.question.UpdateQuestionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public List<Question> getListQuestion();

    public Question getQuestionById(Long id);

    public Question createQuestion(CreateQuestionRequest request);

    public Question updateQuestion(UpdateQuestionRequest reques);

    public void deleteQuestion(Long id);
}
