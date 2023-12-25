package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Chair;
import com.multibase.entity.postgre.Course;
import com.multibase.entity.postgre.Institute;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface PostgreInstituteRepository extends CrudRepository<Institute, Long> {
    Iterable<Institute> findAllByUniversity(University university);
}
