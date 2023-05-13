package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;

import java.time.Year;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(int id);

    void save(Student student);

    void deleteById(int id);

    List<Student> searchAllStudentsByLastName(String lastName);

    List<Student> filterStudentsByYearRange(List<Student> students, int start, int end);

    List<Student> filterStudentsByCurrentMonth(List<Student> studentList);

    List<Student> filterStudentsByInstrumentContaining(List<Student> studentList, String instrument);

}
