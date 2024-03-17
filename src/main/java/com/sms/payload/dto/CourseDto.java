package com.sms.payload.dto;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseDto {

    private int courseId;

    private String courseName;
    
    private String courseAbout;

    @JsonIgnore
    @ManyToMany
    private Set <StudentDto> students = new HashSet<>();

}
