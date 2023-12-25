package com.multibase.entity.neo4j;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
@Data
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
public class Lesson {
    @Id
    private Long id;
    private String lessonName;
    private TypeCourse typeCourse;

    public enum TypeCourse {
        LECTURE, PRACTISE, LABORATORY
    }
}
