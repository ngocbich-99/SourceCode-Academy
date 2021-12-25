package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.Lesson;
import com.example.demo.entity.LessonPass;
import com.example.demo.entity.Section;
import com.example.demo.enums.ResponseEnum;
import com.example.demo.exception.BizException;
import com.example.demo.jwt.SecurityService;
import com.example.demo.jwt.UserPrinciple;
import com.example.demo.model.dto.LessonDTO;
import com.example.demo.model.request.lesson.CreateLessonRequest;
import com.example.demo.model.request.lesson.UpdateLessonRequest;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.LessonPassRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.SectionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private LessonPassRepository lessonPassRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public LessonDTO addLesson(UpdateLessonRequest request) {
        Optional<Section> section = sectionRepository.findById(request.getSectionId());
        if (section.isPresent()) {
            Lesson lesson = new Lesson();
            BeanUtils.copyProperties(request, lesson);
            lesson.setSection(section.get());
            lessonRepository.save(lesson);
        }
        //throw exception
        return null;
    }

    @Override
    public List<LessonDTO> addLessons(List<CreateLessonRequest> request, Long sectionId) {
        Section section = this.findSectionById(sectionId);
        TypeToken<List<Lesson>> typeToken = new TypeToken<List<Lesson>>(){};
        List<Lesson> lessonList = mapper.map(request,typeToken.getType());
        for(Lesson lesson : lessonList){
            lesson.setSection(section);
        }
        TypeToken<List<LessonDTO>> typeTokenDTO = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.saveAll(lessonList),typeTokenDTO.getType());
    }

    @Override
    public List<LessonDTO> updateLessons(List<UpdateLessonRequest> request, Long sectionId) {
        Section section = this.findSectionById(sectionId);
        TypeToken<List<Lesson>> typeToken = new TypeToken<List<Lesson>>(){};
        List<Lesson> lessonList = mapper.map(request,typeToken.getType());
        for(Lesson lesson : lessonList){
            lesson.setSection(section);
        }
        TypeToken<List<LessonDTO>> typeTokenDTO = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.saveAll(lessonList),typeTokenDTO.getType());
    }

    @Override
    public List<LessonDTO> getLessons(Section section) {
        TypeToken<List<LessonDTO>> typeToken = new TypeToken<List<LessonDTO>>(){};
        return mapper.map(lessonRepository.findAllBySection(section),typeToken.getType());
    }

    @Override
    public void markAsPass(Long lessonId) {
        UserPrinciple currentUser = securityService.getCurrentUser();
        Optional<Account> currentAccount = accountRepository.findById(currentUser.getId());
        if(currentAccount.isPresent()){
            Lesson currentLesson = findLessonById(lessonId);
            LessonPass lessonPass = new LessonPass();
            lessonPass.setLesson(currentLesson);
            lessonPass.setAccount(currentAccount.get());
            lessonPassRepository.save(lessonPass);
        }
        throw new BizException(ResponseEnum.USERNAME_NOT_EXIST, "user không tồn tại");


    }


    private Section findSectionById(Long id){
        Optional<Section> section = sectionRepository.findById(id);
        if(section.isPresent()){
            return section.get();
        }
        /**
         * Will throw exception in future
         */
        return null;
    }

    private Lesson findLessonById(Long id){
        Optional<Lesson> lesson = lessonRepository.findById(id);
        if(lesson.isPresent()){
            return lesson.get();
        }
//        String message = CommonUtil.getMessage(LOGIN_FAIL);
        throw new BizException(ResponseEnum.LOGIN_FAIL, "Lesson không tồn tại");

    }

}
