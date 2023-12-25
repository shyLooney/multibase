package com.multibase.repository.redis;

import com.multibase.entity.redis.Student;
import org.springframework.data.repository.CrudRepository;

public interface RedisStudentRepository extends CrudRepository<Student, String> {
}
