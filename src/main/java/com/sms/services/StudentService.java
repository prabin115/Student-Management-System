package com.sms.services;

import java.util.List;

import com.sms.entities.Student;
import com.sms.payload.dto.StudentDto;
import com.sms.payload.response.StudentResponse;

public interface StudentService {

    // get list of students
    StudentResponse getAllStudents(int pageNumber, int pageSize, String sortBy);

    // get student by id
    StudentDto getStudentById(int sId);

    // add a new student
    StudentDto addStudent(Student student);

    // update existing student
    StudentDto updateStudent(Student student, int sId);

    // delete a student
    void deleteStudent(int sId);

    // add a course to a student
    StudentDto enrollCourses(int sId, int cId);

    // search student using keyword
    List <StudentDto> searchStudent(String keyword);
}
