package com.multibase.repository.neo4j;

import com.multibase.entity.neo4j.Chair;
import com.multibase.entity.neo4j.Institute;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface NeoChairRepository extends Neo4jRepository<Chair, Long> {

}
