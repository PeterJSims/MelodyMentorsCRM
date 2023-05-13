package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;

import java.time.Year;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student);

    void deleteById(int id);

    List<Student> getStudentsWithinYearRange(Year start, Year end);

    List<Student> findByInstrument(String instrument);

}
