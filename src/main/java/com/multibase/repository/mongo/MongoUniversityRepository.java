package com.multibase.repository.mongo;

import com.multibase.entity.mongo.University;
import org.springframework.data.repository.CrudRepository;

public interface MongoUniversityRepository extends CrudRepository<University, Long> {
}
