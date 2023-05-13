package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsWithinYearRange(Year start, Year end) {
        return studentRepository.findAllByBirthYearBetween(start, end);
    }

    @Override
    public List<Student> findByInstrument(String instrument) {
        return studentRepository.findAllByInstrumentContainingIgnoreCase(instrument);
    }


    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }
}
