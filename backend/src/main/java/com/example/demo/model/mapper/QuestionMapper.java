package com.example.demo.model.mapper;

import com.example.demo.entity.Course;
import com.example.demo.entity.Question;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.request.QuestionReq;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.service.CourseService;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QuestionMapper {
    @Autowired
    private static CourseRepository courseRepository;
    private static CourseService courseService;

    public static Question reqToQuestion(QuestionReq questionReq) {
        Question question = new Question();
        question.setContent(questionReq.getContent());
        question.setAns1(questionReq.getAns1());
        question.setAns2(questionReq.getAns2());
        question.setAns3(questionReq.getAns3());
        question.setAns4(questionReq.getAns4());
        question.setCorrectAns(questionReq.getCorrectAns());
        question.setCreatedTime(questionReq.getCreatedTime());

//        find course theo id front-end tra ve
//        Optional<Course> course = courseRepository.findById(questionReq.getIdCourse());
        Course course = courseService.getCourseById(questionReq.getIdCourse());
        question.setCourse(course);
//
//        if (!course.isPresent()) {
//            throw new NotFoundException("Not found question");
//        } else {
//            question.setCourse(course.get());
//        }

        return question;
    }
}
