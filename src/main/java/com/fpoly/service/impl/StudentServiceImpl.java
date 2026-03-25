
package com.fpoly.service.impl;

import com.fpoly.entity.Student;
import com.fpoly.dao.StudentDAO;
import com.fpoly.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    StudentDAO dao;

    @Override
    public List<Student> findAll() {
        return dao.findAll();
    }

    @Override
    public Student findById(String id) {
        return dao.findById(id).orElse(null);
    }

    @Override
    public Student create(Student student) {
        return dao.save(student);
    }

    @Override
    public Student update(Student student) {
        return dao.save(student);
    }

    @Override
    public void deleteById(String id) {
        dao.deleteById(id);
    }
}

