package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Chair;
import com.multibase.entity.postgre.Institute;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreChairRepository extends CrudRepository<Chair, Long> {
    Iterable<Chair> findAllByInstitute(Institute institute);
}
