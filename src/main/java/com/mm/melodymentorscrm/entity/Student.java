package com.mm.melodymentorscrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Year;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student extends Person {

    @NotBlank(message = "Street Address is required")
    @Size(max = 60, message = "Street address has exceeded the limit")
    @Column(name = "street_address")
    private String streetAddress;

    @NotBlank(message = "Zip code is required")
    @Size(max = 5, message = "Zip code has exceeded the limit")
    @Column(name = "zip")
    private String zip;

    @NotBlank(message = "Instrument type is required")
    @Size(max = 15, message = "Instrument type has exceeded the limit")
    @Column(name = "instrument")
    private String instrument;

    @NotBlank(message = "Teacher name is required")
    @Size(max = 25, message = "Teacher name has exceeded the limit")
    @Column(name = "teacher_name")
    private String teacherName;

    @NotNull(message = "Birth year is required")
    @Min(1900)
    @Max(2500)
    @Column(name = "birth_year")
    private int birthYear;

    @NotNull(message = "Start date is required")
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;


    public Student() {
    }

    public Student(String firstName, String lastName, String email, String phone, String streetAddress, String zip, String instrument, String teacherName, int birthYear, Date startDate) {
        super(firstName, lastName, email, phone);
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.instrument = instrument;
        this.teacherName = teacherName;
        this.birthYear = birthYear;
        this.startDate = startDate;
    }


    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    @Override
    public String toString() {
        return "Name: " + super.getFirstName() + " " + super.getLastName() + ", Instrument: " + getInstrument() + ", Teacher: " + getTeacherName() + ", Student since: " + getStartDate().toString();
    }
}
