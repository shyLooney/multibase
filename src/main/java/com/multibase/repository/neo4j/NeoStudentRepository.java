package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Institute;
import com.multibase.entity.neo4j.Student;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface NeoStudentRepository extends Neo4jRepository<Student, Long> {

    @Query("MATCH (l:Lesson{id:$lessonId})--(c:Course)--(g:StudentGroup)--(s:Student) return s")
    Iterable<Student> findAllStudents(@Param("lessonId") Long id);
}
