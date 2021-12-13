package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.model.request.QuestionReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public List<Question> getListQuestion();

    public Question getQuestionById(int id);

    public Question createQuestion(QuestionReq questionReq);

    public Question updateQuestion(QuestionReq questionReq, int id);

    public void deleteQuestion(int id);
}
