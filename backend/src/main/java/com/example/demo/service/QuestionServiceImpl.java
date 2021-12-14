package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Question;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.mapper.QuestionMapper;
import com.example.demo.model.request.QuestionReq;
import com.example.demo.repository.CourseRepository;
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

    @Autowired
    private CourseRepository courseRepository;

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
        question.setContent(questionReq.getContent());
        question.setAns1(questionReq.getAns1());
        question.setAns2(questionReq.getAns2());
        question.setAns3(questionReq.getAns3());
        question.setAns4(questionReq.getAns4());
        question.setCorrectAns(questionReq.getCorrectAns());
        question.setCreatedTime(questionReq.getCreatedTime());

        Optional<Course> course = courseRepository.findById(questionReq.getIdCourse());

        if (!course.isPresent()) {
            throw new NotFoundException("Course not found");
        } else {
            question.setCourse(course.get());
        }

        questionRepository.save(question);

        return question;
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
