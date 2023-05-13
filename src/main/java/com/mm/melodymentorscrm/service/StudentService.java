package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;

import java.time.Year;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void save(Student student);

    void deleteById(int id);

    List<Student> filterStudentsByYearRange(List<Student> students, Year start, Year end);

    List<Student> filterStudentsByCurrentMonth(List<Student> studentList);

    List<Student> filterStudentsByInstrumentContaining(List<Student> studentList, String instrument);

}
