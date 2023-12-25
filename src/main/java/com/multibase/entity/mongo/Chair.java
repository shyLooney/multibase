package com.multibase.entity.mongo;

import com.multibase.entity.postgre.Institute;
import com.multibase.entity.postgre.Speciality;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Chair {
    @Id
    private Long id;
    private String chairName;
    private String chairCode;
}

