package com.example.demo.common.type;


import com.example.demo.model.dto.*;
import com.google.common.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;

public class TypeConstant {

    private TypeConstant() {
    }

    private Type LIST_COURSE_DTO_TYPE = new TypeToken<Collection<CourseDTO>>() {
    }.getType();

    private Type LIST_LONG = new TypeToken<Collection<Long>>() {
    }.getType();

    private Type LIST_CATEGORY_DTO_TYPE = new TypeToken<Collection<CategoryDTO>>() {
    }.getType();

    private Type LIST_LESSON_DTO_TYPE = new TypeToken<Collection<LessonDTO>>() {
    }.getType();

    private Type LIST_SECTION_TYPE = new TypeToken<Collection<SectionDTO>>() {
    }.getType();

    private Type LIST_ACCOUNT_TYPE = new TypeToken<Collection<AccountDTO>>() {
    }.getType();


}
