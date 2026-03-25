package com.fpoly.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpoly.entity.Student;

public interface StudentDAO extends JpaRepository<Student, String> {
}