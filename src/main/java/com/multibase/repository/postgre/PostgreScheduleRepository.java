package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Schedule;
import com.multibase.entity.postgre.University;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PostgreScheduleRepository  extends CrudRepository<Schedule, Long> {
    List<Schedule> findAllByDateIsAfterAndDateIsBefore(Date end, Date start);
}
