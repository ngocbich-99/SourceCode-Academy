package com.example.demo.model.dto;

import com.example.demo.entity.LessonPass;
import com.example.demo.entity.Section;
import com.example.demo.entity.Test;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
public class LessonDTO {

    private Long id;

    private String name;

    private String urlVideo;

    private Long createdTime;

    private String type;

    private String description;

}
