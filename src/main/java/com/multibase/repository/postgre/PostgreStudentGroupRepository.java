package com.multibase.repository.postgre;

import com.multibase.entity.postgre.StudentGroup;
import org.springframework.data.repository.CrudRepository;

public interface PostgreStudentGroupRepository extends CrudRepository<StudentGroup, Long> {
}
