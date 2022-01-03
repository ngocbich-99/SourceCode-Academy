package com.example.demo.convert;

import com.example.demo.entity.Account;
import com.example.demo.model.dto.SectionDTO;
import com.google.gson.Gson;
import jdk.nashorn.internal.parser.TokenType;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class Converter{

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private Gson gson;

    public Converter(){
    }

    private <T> T gsonFromJsonObject(String json, Class<T> clazz) {
        mapper.map(clazz,clazz);
        return gson.fromJson(json, clazz);
    }

    private <T> Collection<T> gsonFromJsonArray(String json, Type type) {
        return gson.fromJson(json,type);
    }

    private <T> void map(Object source,T target) {
        mapper.map(source,target);
    }
    private <T> Collection<T> map(Object source,Type type) {
        return mapper.map(source,type);
    }
}
