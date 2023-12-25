package com.multibase.entity.postgre;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class Institute {
    @Id
    private Long id;
    private String instituteName;
    @ManyToOne//(fetch = FetchType.LAZY)
    @JoinColumn
    private University university;
}
