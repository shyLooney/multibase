package com.multibase.inits;

import com.multibase.entity.postgre.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class ArraysCreate {
    @Bean
    public ArrayList<University> universities() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Speciality> specialities() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Institute> institutes() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Chair> chairs() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<StudentGroup> groups() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Course> courses() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Material> materials() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Schedule> schedules() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Visit> visits() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Student> students() {
        return new ArrayList<>();
    }
    @Bean
    public ArrayList<Lesson> lessons() {return new ArrayList<>();}

}
