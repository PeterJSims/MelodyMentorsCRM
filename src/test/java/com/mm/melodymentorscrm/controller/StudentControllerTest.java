package com.mm.melodymentorscrm.controller;

import com.mm.melodymentorscrm.entity.Student;
import com.mm.melodymentorscrm.repository.StudentRepository;
import com.mm.melodymentorscrm.service.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestPropertySource("/application-dev.properties")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class StudentControllerTest {

    private static MockHttpServletRequest request;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentServiceImpl mockStudentService;

    @Autowired
    Student student;



    @Test
    void listStudents() throws Exception {
        Student student1 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        Student student2 = new Student("Loretta", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        List<Student> studentList = new ArrayList<>(Arrays.asList(student1, student2));
        when(mockStudentService.findAll()).thenReturn(studentList);
        assertEquals("Sophia", mockStudentService.findAll().get(0).getFirstName());
        assertEquals("Loretta", mockStudentService.findAll().get(1).getFirstName());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "home");
    }

    @Test
    void listStudentsByAge() throws Exception {
        Student student1 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        List<Student> studentList = new ArrayList<>(Arrays.asList(student1));
        when(mockStudentService.filterStudentsByYearRange(studentList, 1900, 2020)).thenReturn(studentList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/studentsByAge")).andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "studentsByAge");
    }

    @Test
    void listStudentsByInstrument() throws Exception {
        Student student1 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        Student student2 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Drums", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        List<Student> studentList = new ArrayList<>(Arrays.asList(student1, student2));
        when(mockStudentService.filterStudentsByInstrumentContaining(studentList, "test")).thenReturn(studentList);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/studentsByInstrument")).andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "studentsByInstrument");
    }

    @Test
    void listStudentsByCurrentAnniversary() throws Exception {
        Student student1 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf(LocalDate.now()));
        Student student2 = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Drums", "Hugo Garcia",
                2003, Date.valueOf("2022-03-01"));
        List<Student> studentList = new ArrayList<>(Arrays.asList(student1, student2));
        when(mockStudentService.filterStudentsByInstrumentContaining(studentList, "test")).thenReturn(studentList);
        assertEquals(0, mockStudentService.filterStudentsByCurrentMonth(studentList).size());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/anniversaries")).andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "anniversaries");
    }

    @Test
    void showFormForAddStudent() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/showFormForAdd")).andExpect(status().isOk()).andReturn();
        ModelAndView mav = mvcResult.getModelAndView();

        ModelAndViewAssert.assertViewName(mav, "student-form");
    }
}
