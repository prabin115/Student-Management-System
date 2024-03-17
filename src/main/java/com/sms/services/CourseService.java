package com.sms.services;

import java.util.List;

import com.sms.entities.Course;
import com.sms.payload.dto.CourseDto;

public interface CourseService {
    
    // get list of courses
    public List <CourseDto> getAllCourses();

    // get courses by id
    public CourseDto getCourseById(int Id);

    // add a new course
    public CourseDto addCourse(Course course);

    // update existing course
    public CourseDto updateCourse(Course course, int Id);

    // delete a course
    public void deleteCourse(int Id);

    
}
