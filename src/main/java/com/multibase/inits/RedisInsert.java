package com.multibase.inits;

import com.multibase.entity.redis.Student;
import com.multibase.repository.mongo.MongoUniversityRepository;
import com.multibase.repository.redis.RedisStudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;

@Configuration
@DependsOn("generator")
public class RedisInsert {
    private ArrayList<com.multibase.entity.postgre.Student> students;

    public RedisInsert(ArrayList<com.multibase.entity.postgre.Student> students) {
        this.students = students;
    }

    @Bean
    public CommandLineRunner insertRedisStudent(RedisStudentRepository repository) {
        ArrayList<Student> arrayList = new ArrayList<>();

        for (var item : students) {
            arrayList.add(new Student(
                    item.getStudentCode(),
                    item.getFullName(),
                    item.getBirthday(),
                    item.getStudentGroup().getId(),
                    item.getId()));
        }
        repository.saveAll(arrayList);

        return args -> {

        };
    }
}
