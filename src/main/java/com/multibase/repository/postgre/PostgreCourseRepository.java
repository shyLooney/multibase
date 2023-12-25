package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Chair;
import com.multibase.entity.postgre.Course;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreCourseRepository extends CrudRepository<Course, Long> {
    Iterable<Course> findAllByChair(Chair chair);
}
