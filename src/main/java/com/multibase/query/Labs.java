package com.multibase.query;

import com.multibase.entity.postgre.Student;
import com.multibase.entity.postgre.StudentGroup;
import com.multibase.repository.elastic.ElasticMaterialRepository;
import com.multibase.repository.neo4j.NeoLessonRepository;
import com.multibase.repository.neo4j.NeoStudentRepository;
import com.multibase.repository.neo4j.NeoUniversityRepository;
import com.multibase.repository.postgre.PostgreScheduleRepository;
import com.multibase.repository.postgre.PostgreStudentRepository;
import com.multibase.repository.postgre.PostgreVisitRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Configuration
public class Labs {
    JdbcTemplate jdbcTemplate;

    public Labs(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    String executeLabOne(ElasticMaterialRepository elasticMaterialRepository,
                         NeoStudentRepository neoStudentRepository,
                         PostgreVisitRepository postgreVisitRepository,
                         PostgreScheduleRepository postgreScheduleRepository,
                         PostgreStudentRepository postgreStudentRepository) {
        System.out.println("SEARCH START");

        String word = "lorem";
        Date dateFrom = Date.from(Instant.parse("2023-09-01T08:00:00Z"));
        Date dateTo = Date.from(Instant.parse("2023-12-23T20:00:00Z"));

        var materials = elasticMaterialRepository.findAllByContentContains(word);

        ArrayList<Student> arrayList = new ArrayList<>();
        for (var item : materials) {
            for (var st : neoStudentRepository.findAllStudents(item.getId())) {
                arrayList.add(new Student(st.getId(), st.getFullName(),  st.getStudentCode(), st.getBirthday(),
            new StudentGroup(null, null, (byte) 0, null, null)));
            }
        }
//
//        ArrayList<Node> nodes = new ArrayList<>();
//        for (var item : arrayList) {
//            Node node = new Node(item, word);
//            nodes.add(node);
//            for (var sch : postgreScheduleRepository.findAllByDateIsAfterAndDateIsBefore(dateTo, dateFrom)) {
//                node.addVisit(postgreVisitRepository.findByStudentAndSchedule(item, sch).isVisit());
//            }
//        }

        StringBuilder str = new StringBuilder();
        for (var item : arrayList) {
            str.append(item.getId()).append(",");
        }
        str.deleteCharAt(str.length() - 1);


        String sqlQuery = "SELECT (CAST(SUM(CASE WHEN visit THEN 1 ELSE 0 END) AS FLOAT) / COUNT(*)) AS percent," +
                "student_id, fullname, birthday, studentcode " +
                "    FROM visit\n" +
                "    JOIN schedule s on visit.schedule_id = s.id\n" +
                "    JOIN student s2 on visit.student_id = s2.id" +
                "    WHERE student_id in (" + str.toString() + ")\n" +
                "    AND s.date between '" + dateFrom + "' AND '"+ dateTo + "'\n" +
                "    GROUP BY student_id, fullname, birthday, studentcode\n" +
                "    ORDER BY percent\n" +
                "    LIMIT 100";

        List<Map<String, Object>> result = jdbcTemplate.queryForList(sqlQuery);

        for (Map<String, Object> row : result) {
            Double percent = (Double) row.get("percent");
            Long studentId = (Long) row.get("student_id");
            String fullname = (String) row.get("fullname");
            String studentcode = (String) row.get("studentcode");
            Date birthday = (Date) row.get("birthday");


            System.out.println("Word: " + word + "; From: " + dateFrom + "; To: " + dateTo +
                    "; Percent: " + percent + "; Student ID: " + studentId +
                    "; Code: " + studentcode + "; Birthday: " + birthday + "; FIO: " + fullname);
        }

        return new String("hell");
    }

    class Node {
        Student student;
        String word;
        double percent;
        double visits;
        int size;

        public Node(Student student, String word) {
            percent = 0;
            size = 0;
            visits = 0;
            this.student = student;
            this.word = word;
        }

        private void addVisit(boolean val) {
            visits += val ? 1 : 0;
            size++;
            percent = visits / size;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "student=" + student +
                    ", word='" + word + '\'' +
                    ", percent=" + percent +
                    ", visits=" + visits +
                    ", size=" + size +
                    '}';
        }
    }
}
