package com.multibase.inits;

import com.multibase.entity.mongo.Chair;
import com.multibase.entity.mongo.Institute;
import com.multibase.entity.mongo.University;
import com.multibase.repository.mongo.MongoUniversityRepository;
import com.multibase.repository.postgre.PostgreUniversityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;

@Configuration
@DependsOn("generator")
public class MongoInsert {
    private ArrayList<com.multibase.entity.postgre.University> universities;
    private ArrayList<com.multibase.entity.postgre.Institute> institutes;
    private ArrayList<com.multibase.entity.postgre.Chair> chairs;

    public MongoInsert(ArrayList<com.multibase.entity.postgre.University> universities,
                       ArrayList<com.multibase.entity.postgre.Institute> institutes,
                       ArrayList<com.multibase.entity.postgre.Chair> chairs) {
        this.universities = universities;
        this.institutes = institutes;
        this.chairs = chairs;
    }

    @Bean
    public CommandLineRunner insertMongoUniversities(MongoUniversityRepository repository) {

        ArrayList<University> universitiesAdapt = new ArrayList<>();
        ArrayList<Institute> institutesAdapt = new ArrayList<>();
        ArrayList<Chair> chairsAdapt  = new ArrayList<>();
        System.out.println("Mongo insert start");


        for (var item : universities) {
            ArrayList<Institute> instituteList = new ArrayList<>();
            universitiesAdapt.add(new University(item.getId(),
                    item.getUniversityName(), instituteList));
            for (var inst : institutes) {
                if (Objects.equals(item.getId(), inst.getUniversity().getId())) {
                    ArrayList<Chair> chairsList = new ArrayList<>();
                    Institute institute = new Institute(inst.getId(),
                            inst.getInstituteName(), chairsList);

                    instituteList.add(institute);
//                    institutes.remove(inst);

                    for (var ch : chairs) {
                        if (Objects.equals(inst.getId(), ch.getInstitute().getId())) {
                            Chair chair = new Chair(ch.getId(), ch.getChairName(), ch.getChairCode());

                            chairsList.add(chair);
//                            chairs.remove(ch);
                        }
                    }
                }
            }
        }

        repository.saveAll(universitiesAdapt);

        System.out.println("Mongo insert end");
        return args -> {

        };
    }
}
