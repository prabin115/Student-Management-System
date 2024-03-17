package com.sms.entities;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;   

    @NotBlank
    private String studentName;

    @Email
    private String email;

    @ManyToMany
    @JoinTable(name = "student_courses", 
        joinColumns = 
            @JoinColumn(name = "student_id", referencedColumnName = "studentId"),
        inverseJoinColumns = 
            @JoinColumn(name = "course_id", referencedColumnName = "courseId"))
    private Set <Course> courses = new HashSet<>();

    public void enrollStudent(Course course) {
        courses.add(course);
    }
}
