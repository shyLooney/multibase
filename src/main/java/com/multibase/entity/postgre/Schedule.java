package com.multibase.entity.postgre;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Schedule {
    @Id
    private Long id;
    private int week;
    private String classroom;
    private String teacher;
    private Date date;
    @ManyToOne
    @JoinColumn
    private StudentGroup studentGroup;
    @ManyToOne
    @JoinColumn
    private Lesson lesson;

}
