package com.multibase.entity.mongo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
public class University {
    @Id
    private Long id;
    private String universityName;
    List<Institute> institutes = new ArrayList<>();

    public void addInstitute(Institute institute) {
        institutes.add(institute);
    }
}
