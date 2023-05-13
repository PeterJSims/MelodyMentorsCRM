package com.mm.melodymentorscrm.controller;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String listStudents(Model model) {
        List<Student> studentList = studentService.findAll();

        model.addAttribute("students", studentList);
        return "home";
    }
}
