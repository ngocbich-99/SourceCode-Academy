package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.mapper.QuestionMapper;
import com.example.demo.model.request.QuestionReq;
import com.example.demo.repository.QuestionRepository;
import jdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getListQuestion() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    @Override
    public Question getQuestionById(int id) {
        Optional<Question> question = questionRepository.findById(id);
        if (!question.isPresent()) {
            throw new NotFoundException("Not found question");
        }
        return question.get();
    }

    @Override
    public Question createQuestion(QuestionReq questionReq) {
        Question rs = questionRepository.findByContent(questionReq.getContent());
        if (rs != null) {
            throw new InternalException("Question is already in db");
        }

//        convert questionReq => question
        Question question = new Question();
        question = QuestionMapper


        return null;
    }

    @Override
    public Question updateQuestion(QuestionReq questionReq, int id) {
        return null;
    }

    @Override
    public void deleteQuestion(int id) {
//         tim bai kiem tra theo id cau hoi -> neu !== null thi ko duoc xoa

        Optional<Question> question = questionRepository.findById(id);
        if (!question.isPresent()) {
            throw new NotFoundException("Not found question");
        }
        try {
            questionRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't delete question");
        }
    }
}
