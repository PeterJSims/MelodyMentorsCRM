package com.mm.melodymentorscrm.controller;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/", "/home", "/search"})
    public String listStudents(Model model, String keyword) {
        List<Student> studentList;
        if (keyword != null) {
            studentList = studentService.searchAllStudentsByLastName(keyword);
        } else {
            studentList = studentService.findAll();
        }
        model.addAttribute("students", studentList);
        return "home";
    }


    @GetMapping("/studentsByAge")
    public String listStudentsByAge(Model model) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        model.addAttribute("currentDateTime", currentDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss")));

        List<Student> studentList = studentService.findAll();

        LocalDate currentDate = LocalDate.now();
        int currentYear = Year.now().getValue();
        model.addAttribute("currentYear", currentYear);

        List<Student> students3to5 = studentService.filterStudentsByYearRange(studentList, currentYear - 3, currentYear - 5);
        List<Student> students6to9 = studentService.filterStudentsByYearRange(studentList, currentYear - 6, currentYear - 9);
        List<Student> students10to14 = studentService.filterStudentsByYearRange(studentList, currentYear - 10, currentYear - 14);
        List<Student> students15to19 = studentService.filterStudentsByYearRange(studentList, currentYear - 15, currentYear - 19);
        List<Student> studentsAdult = studentService.filterStudentsByYearRange(studentList, currentYear - 20, currentYear - 300);


        model.addAttribute("students3to5", students3to5);
        model.addAttribute("students6to9", students6to9);
        model.addAttribute("students10to14", students10to14);
        model.addAttribute("students15to19", students15to19);
        model.addAttribute("studentsAdult", studentsAdult);
        return "studentsByAge";
    }

    @GetMapping("/studentsByInstrument")
    public String listStudentsByInstrument(Model model) {

        LocalDateTime currentDate = LocalDateTime.now();
        model.addAttribute("currentDate", currentDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm")));

        LocalDateTime currentDateTime = LocalDateTime.now();
        model.addAttribute("currentDateTime", currentDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss")));

        List<Student> studentList = studentService.findAll();

        List<Student> studentsGuitar = studentService.filterStudentsByInstrumentContaining(studentList, "guitar");
        List<Student> studentsPiano = studentService.filterStudentsByInstrumentContaining(studentList, "piano");
        List<Student> studentsVoice = studentService.filterStudentsByInstrumentContaining(studentList, "voice");
        List<Student> studentsBass = studentService.filterStudentsByInstrumentContaining(studentList, "bass");
        List<Student> studentsDrums = studentService.filterStudentsByInstrumentContaining(studentList, "drum");
        List<Student> studentsSaxophone = studentService.filterStudentsByInstrumentContaining(studentList, "sax");
        List<Student> studentsViolinViola = studentService.filterStudentsByInstrumentContaining(studentList, "viol");
        List<Student> studentsBrass = studentService.filterStudentsByInstrumentContaining(studentList, "brass");

        model.addAttribute("studentsGuitar", studentsGuitar);
        model.addAttribute("studentsPiano", studentsPiano);
        model.addAttribute("studentsVoice", studentsVoice);
        model.addAttribute("studentsBass", studentsBass);
        model.addAttribute("studentsDrums", studentsDrums);
        model.addAttribute("studentsSaxophone", studentsSaxophone);
        model.addAttribute("studentsViolinViola", studentsViolinViola);
        model.addAttribute("studentsBrass", studentsBrass);
        return "studentsByInstrument";
    }

    @GetMapping("/anniversaries")
    public String listStudentsByCurrentAnniversary(Model model) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        model.addAttribute("currentDateTime", currentDateTime.format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm:ss")));

        List<Student> studentList = studentService.findAll();
        studentList = studentService.filterStudentsByCurrentMonth(studentList);

        model.addAttribute("filteredStudents", studentList);
        return "anniversaries";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAddStudent(Model model) {
        Student student = new Student();
        student.setBirthYear(2000); // avoids the default of 0
        model.addAttribute("student", student);
        return "student-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdateStudent(@RequestParam("studentId") int id, Model model) {
        Student student = studentService.findById(id);

        model.addAttribute("student", student);
        return "student-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("studentId") int id) {
        studentService.deleteById(id);
        return "redirect:/home";
    }

    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, Errors errors) {
        if (errors.hasErrors()) return "student-form";

        studentService.save(student);
        return "redirect:/home";
    }
}