package com.multibase.repository.postgre;

import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PostgreUniversityRepository extends CrudRepository<University, Long> {
}
