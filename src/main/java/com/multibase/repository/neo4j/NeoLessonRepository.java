package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Chair;
import com.multibase.entity.neo4j.Lesson;
import com.multibase.entity.neo4j.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface NeoLessonRepository extends Neo4jRepository<Lesson, Long> {

}
