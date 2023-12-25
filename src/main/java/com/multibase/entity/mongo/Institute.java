package com.multibase.entity.mongo;

import com.multibase.entity.postgre.University;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class Institute {
    @Id
    private Long id;
    private String instituteName;
    List<Chair> chairList = new ArrayList<>();

    public void addChair(Chair chair) {
        chairList.add(chair);
    }
}
