package com.mm.melodymentorscrm.repository;

import com.mm.melodymentorscrm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Year;
import java.util.Date;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    public List<Student> findAllByInstrumentContainingIgnoreCase(String instrument);

}
