package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Chair;
import com.multibase.entity.neo4j.Course;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoCourseRepository extends Neo4jRepository<Course, Long> {

}
