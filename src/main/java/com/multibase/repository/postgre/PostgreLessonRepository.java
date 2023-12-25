package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Lesson;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

public interface PostgreLessonRepository extends CrudRepository<Lesson, Long> {
}
