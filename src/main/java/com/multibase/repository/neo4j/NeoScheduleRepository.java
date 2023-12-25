package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Schedule;
import com.multibase.entity.neo4j.Speciality;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoScheduleRepository extends Neo4jRepository<Schedule, Long> {

}
