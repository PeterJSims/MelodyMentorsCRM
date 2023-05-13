package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public Student findById(int id) {
        Optional<Student> result = studentRepository.findById(id);

        Student student = null;

        if (result.isPresent()) {
            student = result.get();
        } else {
            throw new RuntimeException("Did not find student with id - " + id);
        }
        return student;
    }

    @Override
    public List<Student> filterStudentsByYearRange(List<Student> studentList, int start, int end) {
        studentList = studentList.stream().filter(
                student -> {
                    int birthYear = student.getBirthYear();
                    if (birthYear == start || birthYear == end) {
                        return true;
                    } else {
                        return (birthYear < (start) && birthYear > (end));
                    }
                }).toList();
        return studentList;
    }

    @Override
    public List<Student> filterStudentsByCurrentMonth(List<Student> studentList) {
        Date currentDate = Date.from(Instant.now());

        studentList = studentList.stream().filter(
                student -> {
                    Date date = student.getStartDate();
                    Calendar cal1 = Calendar.getInstance();
                    Calendar cal2 = Calendar.getInstance();
                    cal1.setTime(date);
                    cal2.setTime(currentDate);
                    return cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
                }).toList();
        return studentList;
    }

    @Override
    public List<Student> filterStudentsByInstrumentContaining(List<Student> studentList, String instrument) {
        studentList = studentList.stream().filter(
                student -> student.getInstrument().toLowerCase().contains(instrument.toLowerCase())
        ).toList();
        return studentList;
    }


    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteById(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> searchAllStudentsByLastName(String lastName) {
        return studentRepository.findAllByLastNameStartingWithIgnoreCase(lastName);
    }
}
