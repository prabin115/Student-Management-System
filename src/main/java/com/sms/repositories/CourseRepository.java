package com.sms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entities.Course;

public interface CourseRepository extends JpaRepository <Course, Integer> {
    
}
