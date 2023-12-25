package com.multibase.entity.postgre;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Course {
    @Id
    private Long id;
    private String courseName;
    private TypeCourse type;
    private byte step;
    private String description;
    @ManyToOne
    @JoinColumn
    private Chair chair;
    @ManyToMany
    private List<StudentGroup> studentGroupList;

    public void addGroup(StudentGroup studentGroup) {
        studentGroupList.add(studentGroup);
    }

    public enum TypeCourse {
        EXAM, TEST, DISTANT_TEST
    }
}
