package com.multibase.repository.postgre;

import com.multibase.entity.postgre.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostgreLabOne extends JpaRepository<Visit, Long> {
//    @Query("SELECT (CAST(SUM(CASE WHEN visit THEN 1 ELSE 0 END) AS FLOAT) / COUNT(*)) AS percent, student_id " +
//            "FROM visit " +
//            "JOIN schedule s on visit.schedule_id = s.id " +
//            "WHERE student_id in (:stId) " +
//            "AND date_visit between ':dateEnd' AND ':dateStart' " +
//            "GROUP BY student_id " +
//            "ORDER BY percent " +
//            "LIMIT :limit")
//    List<Object[]> findBySpecialCondition(@Param("stId") String id, @Param("dateStart") String start,
//                                          @Param("dateEnd") String end, @Param("limit") String limit);
}
