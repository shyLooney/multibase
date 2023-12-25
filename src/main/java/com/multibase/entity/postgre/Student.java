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
public class Student {
    @Id
    private Long id;
    private String fullName;
    private String studentCode;
    private Date birthday;
    @ManyToOne
    @JoinColumn
    private StudentGroup studentGroup;
}
