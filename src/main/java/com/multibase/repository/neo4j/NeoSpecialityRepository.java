package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Chair;
import com.multibase.entity.neo4j.Speciality;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoSpecialityRepository extends Neo4jRepository<Speciality, Long> {

}
