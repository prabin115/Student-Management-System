package com.sms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sms.entities.Student;

public interface StudentRepository extends JpaRepository <Student, Integer> {
    List <Student> findBystudentNameContaining(String name);
}
