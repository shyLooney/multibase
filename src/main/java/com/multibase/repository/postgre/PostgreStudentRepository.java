package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Student;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreStudentRepository  extends CrudRepository<Student, Long> {
    Iterable<Student> findAllByStudentGroupId(Long id);
}
