package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Question;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.model.request.question.CreateQuestionRequest;
import com.example.demo.model.request.question.UpdateQuestionRequest;
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
    public Question getQuestionById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        if (!question.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND,"Not found question");
        }
        return question.get();
    }

    @Override
    public Question createQuestion(CreateQuestionRequest createQuestionRequest) {
//        Question rs = questionRepository.findByContent(questionReq.getContent());
//        if (rs != null) {
//            throw new InternalException("Question is already in db");
//        }
//
////        convert questionReq => question
//        Question question = new Question();

//        convert questionReq => question
//        Question question = new Question();
//        question.setContent(createQuestionRequest.getContent());
//        question.setAns1(createQuestionRequest.getAns1());
//        question.setAns2(createQuestionRequest.getAns2());
//        question.setAns3(createQuestionRequest.getAns3());
//        question.setAns4(createQuestionRequest.getAns4());
//        question.setCorrectAns(createQuestionRequest.getCorrectAns());
//        question.setCreatedTime(createQuestionRequest.getCreatedTime());
//
//        Optional<Course> course = courseRepository.findById(createQuestionRequest.getCourseId());
//
//        if (!course.isPresent()) {
//            throw new NotFoundException("Course not found");
//        } else {
//            question.setCourse(course.get());
//        }
//
//        questionRepository.save(question);

        return null;
    }

    @Override
    public Question updateQuestion(UpdateQuestionRequest request){
        return null;
    }

    @Override
    public void deleteQuestion(Long id) {
//         tim bai kiem tra theo id cau hoi -> neu !== null thi ko duoc xoa

        Optional<Question> question = questionRepository.findById(id);
        if (!question.isPresent()) {
            throw new BizException(ResponseEnum.NOT_FOUND,"Not found question");
        }
        try {
            questionRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalException("Db error. Can't delete question");
        }
    }
}
