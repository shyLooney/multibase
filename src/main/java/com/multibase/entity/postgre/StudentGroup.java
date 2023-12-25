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
public class StudentGroup {
    @Id
    private Long id;
    private String groupName;
    private byte step;
    @ManyToOne
    @JoinColumn
    private Speciality speciality;
    @ManyToOne
    @JoinColumn
    private Chair chair;
}
