package com.sms.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sms.config.AppConstants;
import com.sms.entities.Student;
import com.sms.payload.dto.StudentDto;
import com.sms.payload.response.StudentResponse;
import com.sms.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity <StudentResponse> getAllStudents(
        @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
        @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy){
        return new ResponseEntity<>(this.studentService.getAllStudents(pageNumber, pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity <StudentDto> getStudentById(@PathVariable int id){
        return new ResponseEntity<>(this.studentService.getStudentById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity <StudentDto> addStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(this.studentService.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity <StudentDto> updateStudent(@Valid @RequestBody Student student, @PathVariable int id){
        return new ResponseEntity<>(this.studentService.updateStudent(student, id), HttpStatus.OK);
    }

    @PutMapping("/{sId}/courses/{cId}")
    public ResponseEntity <StudentDto> enrollCourses(@PathVariable int sId, @PathVariable int cId){
        return new ResponseEntity<>(this.studentService.enrollCourses(sId, cId), HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity <List<StudentDto>> findStudentByKeywords(@PathVariable String keywords){
        return new ResponseEntity<>(this.studentService.searchStudent(keywords), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteStudent(@PathVariable int id){
        this.studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
