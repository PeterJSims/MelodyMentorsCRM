package com.mm.melodymentorscrm.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "First name is required")
    @Size(max = 25, message = "First name has exceeded the limit")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 25, message = "Last name field has exceeded the limit")
    @Column(name="last_name")
    private String lastName;

    @NotBlank(message = "Email address is required")
    @Size(max = 25, message = "Email field has exceeded the limit")
    @Column(name="email_address")
    private String emailAddress;

    @NotBlank(message = "Phone number is required")
    @Column(name="phone_number")
    @Size(max = 12, message = "Phone number field has exceeded the limit")
    private String phoneNumber;

    public Person() {
    }

    public Person(String firstName, String lastName, String emailAddress, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
