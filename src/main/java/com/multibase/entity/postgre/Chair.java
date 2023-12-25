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
public class Chair {
    @Id
    private Long id;
    private String chairName;
    private String chairCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Institute institute;
    @ManyToMany
    private List<Speciality> specialityList;

    public void addSpeciality(Speciality speciality) {
        specialityList.add(speciality);
    }
}

