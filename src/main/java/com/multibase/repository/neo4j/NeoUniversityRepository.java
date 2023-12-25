package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Lesson;
import com.multibase.entity.neo4j.Student;
import com.multibase.entity.neo4j.University;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface NeoUniversityRepository extends Neo4jRepository<University, Long> {

}
