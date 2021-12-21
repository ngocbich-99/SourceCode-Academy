package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.model.request.question.CreateQuestionRequest;
import com.example.demo.model.request.question.UpdateQuestionRequest;
import com.example.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @CrossOrigin
    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        List<Question> listQuestion = questionService.getListQuestion();
        return ResponseEntity.ok(listQuestion);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Question question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> createQuestion(@Valid @RequestBody CreateQuestionRequest createQuestionRequest) {
        Question question = questionService.createQuestion(createQuestionRequest);
        return ResponseEntity.ok(question);
    }

    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editQuestion(@Valid @RequestBody UpdateQuestionRequest body) {
        return ResponseEntity.ok(questionService.updateQuestion(body));
    }

    //    chi duoc phep xoa cau hoi khi cau hoi do chua su dung trong bai kiem tra nao
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.ok("Delete category success");
    }
}
