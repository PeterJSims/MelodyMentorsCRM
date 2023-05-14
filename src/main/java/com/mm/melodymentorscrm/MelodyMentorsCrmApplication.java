package com.mm.melodymentorscrm;

import com.mm.melodymentorscrm.entity.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class MelodyMentorsCrmApplication {


    public static void main(String[] args) {
        SpringApplication.run(MelodyMentorsCrmApplication.class, args);
    }


    // Added for unit testing
    @Bean
    @Scope(value = "prototype")
    @Qualifier("student")
    Student getStudent() {
        return new Student();
    }
}
