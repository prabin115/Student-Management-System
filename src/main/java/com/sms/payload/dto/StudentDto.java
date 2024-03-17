package com.sms.payload.dto;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDto {

    private int studentId;
    
    private String studentName;
    
    private String email;
    
    @ManyToMany(mappedBy = "students")
    private Set <CourseDto> courses = new HashSet<>();

}
