package com.mm.melodymentorscrm.repository;

import com.mm.melodymentorscrm.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByLastNameStartingWithIgnoreCase(String instrument);

}
