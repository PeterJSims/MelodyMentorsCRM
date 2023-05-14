package com.mm.melodymentorscrm.service;

import com.mm.melodymentorscrm.entity.Student;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application-dev.properties")
@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentServiceImpl studentService;

    @BeforeAll
    public void beforeAll() {
        jdbcTemplate.execute("DELETE FROM student");
    }

    @BeforeEach
    public void beforeEach() {
        jdbcTemplate.execute("INSERT INTO student (first_name, last_name, instrument, start_date, birth_year, street_address, zip, phone_number,\n" +
                "                     email_address, teacher_name)\n" +
                "VALUES ('John', 'Smith', 'Saxophone', '2019-05-01', 2011, '123 Main St', '23222', '555-555-1212', 'richard.smith@example.com',\n" +
                "        'John McCloud')");
        jdbcTemplate.execute("INSERT INTO student (first_name, last_name, instrument, start_date, birth_year, street_address, zip, phone_number,\n" +
                "                     email_address, teacher_name)\n" +
                "VALUES ('Michael', 'Williams', 'Drums', '2018-09-05', 2001, '789 Oak St', '23236', '555-555-1414',\n" +
                "        'michael.williams@example.com', 'Brian Stig')");
    }

    @AfterEach
    public void afterEach() {
        jdbcTemplate.execute("DELETE FROM student");
    }

    @Test
    void findAll() {
        List<Student> studentList = studentService.findAll();
        assertEquals(2, studentList.size());
    }

    @Test
    void findById() {
        List<Student> studentList = studentService.findAll();
        int id = studentList.get(0).getId();
        Optional<Student> optionalStudent = Optional.ofNullable(studentService.findById(id));
        assertNotNull(optionalStudent);

        assertThrows(RuntimeException.class, () -> {
            Optional<Student> optionalStudentDoesNotExist = Optional.ofNullable(studentService.findById(500000));
            assertNull(optionalStudentDoesNotExist);
        });

    }

    @Test
    void findByIdDoesNotExist() {
        assertThrows(RuntimeException.class, () -> {
            Optional<Student> optionalStudentDoesNotExist = Optional.ofNullable(studentService.findById(500000));
            assertNull(optionalStudentDoesNotExist);
        });
    }

    @Test
    void filterStudentsByYearRange() {
        List<Student> studentList = studentService.filterStudentsByYearRange(studentService.findAll(), 2000, 2030);
        assertEquals(0, studentList.size());
    }

    @Test
    void filterStudentsByCurrentMonth() {
        Student student = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf(LocalDate.now()));
        List<Student> students = studentService.findAll();
        studentService.save(student);
        List<Student> filteredStudentList = studentService.filterStudentsByCurrentMonth(students);
        assertTrue(filteredStudentList.size() <= students.size());
    }

    @Test
    void filterStudentsByInstrumentContaining() {
        List<Student> studentList = studentService.findAll();

        List<Student> saxStudents = studentService.filterStudentsByInstrumentContaining(studentList, "sax");
        assertTrue(saxStudents.size() >= 1);
        List<Student> kazooStudents = studentService.filterStudentsByInstrumentContaining(studentList, "kazoo");
        assertEquals(0, kazooStudents.size());
    }

    @Test
    void save() {
        List<Student> beforeSave = studentService.findAll();
        Student student = new Student("Sophia", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                2003, Date.valueOf(LocalDate.now()));
        studentService.save(student);
        List<Student> afterSave = studentService.findAll();
        assertTrue(beforeSave.size() + 1 == afterSave.size());
        assertThrows(ConstraintViolationException.class, () -> {
            Student studentTwo = new Student("", "Wilson", "test@email.com", "555-123-2222", "579 Maple Rd", "23222", "Guitar", "Hugo Garcia",
                    1216, Date.valueOf(LocalDate.now()));
            studentService.save(studentTwo);
        });
    }

    @Test
    void deleteById() {
        List<Student> studentListBeforeDelete = studentService.findAll();
        int id = studentListBeforeDelete.get(0).getId();
        studentService.deleteById(id);
        List<Student> studentListAfterDelete = studentService.findAll();
        assertTrue(studentListBeforeDelete.size() > studentListAfterDelete.size());
    }

    @Test
    void searchAllStudentsByLastName() {
        List<Student> studentsWithLastNameStart = studentService.searchAllStudentsByLastName("smi");
        assertEquals(1, studentsWithLastNameStart.size());
        List<Student> noStudentsExpected = studentService.searchAllStudentsByLastName("xyzxyz");
        assertEquals(0, noStudentsExpected.size());
    }
}