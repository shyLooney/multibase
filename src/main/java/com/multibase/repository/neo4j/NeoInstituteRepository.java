package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Institute;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoInstituteRepository extends Neo4jRepository<Institute, Long> {

}
