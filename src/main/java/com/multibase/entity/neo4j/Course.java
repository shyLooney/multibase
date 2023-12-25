package com.multibase.entity.neo4j;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
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
    @Relationship(direction = OUTGOING)
    private Set<StudentGroup> groupsList = new HashSet<>();
    @Relationship(direction = OUTGOING)
    private Set<Lesson> lessonsList = new HashSet<>();


    public enum TypeCourse {
        EXAM, TEST, DISTANT_TEST
    }
}
