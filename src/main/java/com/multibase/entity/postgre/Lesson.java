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
public class Lesson {
    @Id
    private Long id;
    private String lessonName;
    private TypeCourse typeCourse;
    @ManyToOne
    @JoinColumn
    private Course course;
    @ManyToOne
    @JoinColumn
    private Material material;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Material> materialList;


    public enum TypeCourse {
        LECTURE, PRACTISE, LABORATORY
    }
}
