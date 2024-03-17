package com.sms.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entities.Course;
import com.sms.exceptions.ResourceNotFoundException;
import com.sms.payload.dto.CourseDto;
import com.sms.repositories.CourseRepository;
import com.sms.services.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    // get all courses
    @Override
    public List<CourseDto> getAllCourses() {

        List<Course> courses = this.courseRepository.findAll();    

        List<CourseDto> courseDtos = courses.stream().map((course) -> this.modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());

        return courseDtos;
    }

    // get student by Id
    @Override
    public CourseDto getCourseById(int Id) {
        Course course = this.courseRepository.findById(Id).orElseThrow(()-> new ResourceNotFoundException("Course", "course_id", Id));
        return this.modelMapper.map(course, CourseDto.class);
    }

    // add a new student
    @Override
    public CourseDto addCourse(Course course) {

        @SuppressWarnings("null")
        Course newCourse = this.courseRepository.save(course);

        CourseDto courseDto = this.modelMapper.map(newCourse, CourseDto.class);

        courseDto.setCourseId(newCourse.getCourseId());

        return courseDto;
    }

    // update an existing student
    @Override
    public CourseDto updateCourse(Course course, int Id) {
        Optional <Course> existingCourse = this.courseRepository.findById(Id);
        if (existingCourse.isPresent()) {
            existingCourse.get().setCourseId(Id);

            @SuppressWarnings("null")
            Course updatedCourse = this.courseRepository.save(course);
            return this.modelMapper.map(updatedCourse, CourseDto.class);
        } else {
            throw new ResourceNotFoundException("Course", "course_id", Id);
        }
    }

    // delete an existing student
    @Override
    public void deleteCourse(int Id) {
        Optional <Course> existingCourse = this.courseRepository.findById(Id);
        if (existingCourse.isPresent()) {
            this.courseRepository.deleteById(Id);
        } else {
            throw new ResourceNotFoundException("Course", "course_id", Id);
        }
    }
    
}
