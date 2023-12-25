package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Schedule;
import com.multibase.entity.postgre.Student;
import com.multibase.entity.postgre.University;
import com.multibase.entity.postgre.Visit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostgreVisitRepository extends CrudRepository<Visit, Long> {
    List<Visit> findAllByStudent(Student student);
    Visit findByStudentAndSchedule(Student student, Schedule schedule);
}
