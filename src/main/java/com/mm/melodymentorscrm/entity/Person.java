package com.mm.melodymentorscrm.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int personId;

    @NotBlank(message = "First name is required")
    @Size(max = 25, message = "First name has exceeded the limit")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 25, message = "Last name field has exceeded the limit")
    private String lastName;

    @NotBlank(message = "Email address is required")
    @Size(max = 25, message = "Email field has exceeded the limit")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 12, message = "Phone number field has exceeded the limit")
    private String phone;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
