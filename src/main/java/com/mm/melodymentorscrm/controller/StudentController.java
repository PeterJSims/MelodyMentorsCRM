package com.mm.melodymentorscrm.controller;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Controller
@RequestMapping("/")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = {"/", "/home"})
    public String listStudents(Model model) {
        List<Student> studentList = studentService.findAll();

        model.addAttribute("students", studentList);
        return "home";
    }


    @GetMapping("/studentsByAge")
    public String listStudentsByAge(Model model) {
        List<Student> studentList = studentService.findAll();

        LocalDate currentDate = LocalDate.now();
        Year currentYear = Year.of(currentDate.getYear());
        model.addAttribute("currentYear", currentYear.getValue());

        List<Student> students3to5 = studentService.filterStudentsByYearRange(studentList, currentYear.minusYears(3), currentYear.minusYears(5));
        List<Student> students6to9 = studentService.filterStudentsByYearRange(studentList, currentYear.minusYears(6), currentYear.minusYears(9));
        List<Student> students10to14 = studentService.filterStudentsByYearRange(studentList, currentYear.minusYears(10), currentYear.minusYears(14));
        List<Student> students15to19 = studentService.filterStudentsByYearRange(studentList, currentYear.minusYears(15), currentYear.minusYears(19));
        List<Student> studentsAdult = studentService.filterStudentsByYearRange(studentList, currentYear.minusYears(20), currentYear.minusYears(120));


        model.addAttribute("students3to5", students3to5);
        model.addAttribute("students6to9", students6to9);
        model.addAttribute("students10to14", students10to14);
        model.addAttribute("students15to19", students15to19);
        model.addAttribute("studentsAdult", studentsAdult);
        return "studentsByAge";
    }

    @GetMapping("/studentsByInstrument")
    public String listStudentsByInstrument(Model model) {
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
        List<Student> studentList = studentService.findAll();
        studentList = studentService.filterStudentsByCurrentMonth(studentList);

        model.addAttribute("filteredStudents", studentList);
        return "anniversaries";
    }
}
