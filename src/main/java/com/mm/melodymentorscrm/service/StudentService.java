package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student);

    void deleteById(int id);
}
