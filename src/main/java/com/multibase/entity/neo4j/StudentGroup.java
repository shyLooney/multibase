package com.multibase.entity.neo4j;

import com.multibase.entity.postgre.Chair;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.data.neo4j.core.schema.Relationship.Direction.OUTGOING;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class StudentGroup {
    @Id
    private Long id;
    private String groupName;
    private byte step;
    @Relationship(direction = OUTGOING)
    private Set<Schedule> schedulesList = new HashSet<>();
    @Relationship(direction = OUTGOING)
    private Set<Student> studentsList = new HashSet<>();
}
