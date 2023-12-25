package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Institute;
import com.multibase.entity.neo4j.StudentGroup;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoStudentGroupRepository extends Neo4jRepository<StudentGroup, Long> {

}
