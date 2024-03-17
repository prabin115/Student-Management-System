package com.sms.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sms.entities.Course;
import com.sms.entities.Student;
import com.sms.exceptions.ResourceNotFoundException;
import com.sms.payload.dto.StudentDto;
import com.sms.payload.response.StudentResponse;
import com.sms.repositories.CourseRepository;
import com.sms.repositories.StudentRepository;
import com.sms.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    // get all students
    @Override
    public StudentResponse getAllStudents(int pageNumber, int pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page <Student> studentList = this.studentRepository.findAll(p);
        
        List<Student> allStudents = studentList.getContent();
        
        List<StudentDto> studentDtos = allStudents.stream().map(student -> this.modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());

        StudentResponse studentResponse = new StudentResponse();

        studentResponse.setContent(studentDtos);
        studentResponse.setPageNumber(studentList.getNumber() + 1);
        studentResponse.setPageSize(studentList.getSize());
        studentResponse.setTotalElements(studentList.getTotalElements());
        studentResponse.setTotalPages(studentList.getTotalPages());
        studentResponse.setLastPage(studentList.isLast());

        return studentResponse;
    }

    // get student by Id
    @Override
    public StudentDto getStudentById(int sId) {

        Student student = this.studentRepository.findById(sId).orElseThrow(()-> new ResourceNotFoundException("Student", "id", sId));
        return this.modelMapper.map(student, StudentDto.class);
    }

    // add a new student
    @Override
    public StudentDto addStudent(Student student) {

        @SuppressWarnings("null")
        Student newStudent = this.studentRepository.save(student);
        StudentDto studentDto = this.modelMapper.map(newStudent, StudentDto.class);
        studentDto.setStudentId(student.getStudentId());
        return studentDto;
    }

    // update an existing student
    @Override
    public StudentDto updateStudent(Student student, int sId) {
        Optional <Student> existingStudent = this.studentRepository.findById(sId);
        
        if (existingStudent.isPresent()) {
            existingStudent.get().setStudentId(sId);

            @SuppressWarnings("null")
            Student updatedStudent = this.studentRepository.save(student);
            return this.modelMapper.map(updatedStudent, StudentDto.class);
        } else {
            throw new ResourceNotFoundException("Student", "id", sId);
        }
    }

    // delete an existing student
    @Override
    public void deleteStudent(int sId) {
        Optional <Student> existingStudent = this.studentRepository.findById(sId);
        if (existingStudent.isPresent()) {
            this.studentRepository.deleteById(sId);
        } else {
            throw new ResourceNotFoundException("Student", "id", sId);
        }
    }

    // add courses to student entities
	@Override
	public StudentDto enrollCourses(int sId, int cId) {
        
        Student student = this.studentRepository.findById(sId).get();
        Course course = this.courseRepository.findById(cId).get();
        
        student.enrollStudent(course);
        this.studentRepository.save(student);

        return this.modelMapper.map(student, StudentDto.class);
	}

    // search student by keyword
    @Override
    public List <StudentDto> searchStudent(String keyword) {

        List <Student> student = this.studentRepository.findBystudentNameContaining(keyword);
        List <StudentDto> studentDtos = student.stream().map((s)-> this.modelMapper.map(s, StudentDto.class)).collect(Collectors.toList());
        return studentDtos;
    }
}
