package com.fpoly.service;

import java.util.List;

import com.fpoly.entity.Student;

public interface StudentService {
    List<Student> findAll();
    Student findById(String id);
    Student create(Student student);
    Student update(Student student);
    void deleteById(String id);
}
