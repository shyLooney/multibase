package com.multibase.inits;

import com.multibase.entity.neo4j.*;
import com.multibase.entity.postgre.Material;
import com.multibase.repository.neo4j.*;
import com.multibase.repository.postgre.*;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;

@Configuration
@DependsOn("generator")
@Log
public class NeoInit {
    private ArrayList<com.multibase.entity.postgre.University> universities;
    private ArrayList<com.multibase.entity.postgre.Speciality> specialities;
    private ArrayList<com.multibase.entity.postgre.Institute> institutes;
    private ArrayList<com.multibase.entity.postgre.Chair> chairs;
    private ArrayList<com.multibase.entity.postgre.StudentGroup> groups;
    private ArrayList<com.multibase.entity.postgre.Course> courses;
    private ArrayList<com.multibase.entity.postgre.Lesson> lessons;
    private ArrayList<com.multibase.entity.postgre.Schedule> schedules;
    private ArrayList<com.multibase.entity.postgre.Student> students;
    private ArrayList<Speciality> tempSpecialities = new ArrayList<>();
    private ArrayList<Chair> tempChairs = new ArrayList<>();
    private ArrayList<StudentGroup> tempGroups = new ArrayList<>();
    private ArrayList<Course> tempCourses = new ArrayList<>();
    private ArrayList<Student> tempStudents = new ArrayList<>();
    private ArrayList<Lesson> tempLessons = new ArrayList<>();
    private ArrayList<Schedule> tempSchedules = new ArrayList<>();

    {
        log.setLevel(Level.WARNING);
    }

    public NeoInit(ArrayList<com.multibase.entity.postgre.University> universities,
                   ArrayList<com.multibase.entity.postgre.Speciality> specialities,
                   ArrayList<com.multibase.entity.postgre.Institute> institutes,
                   ArrayList<com.multibase.entity.postgre.Chair> chairs,
                   ArrayList<com.multibase.entity.postgre.StudentGroup> groups,
                   ArrayList<com.multibase.entity.postgre.Course> courses,
                   ArrayList<com.multibase.entity.postgre.Lesson> lessons,
                   ArrayList<com.multibase.entity.postgre.Schedule> schedules,
                   ArrayList<com.multibase.entity.postgre.Student> students) {
        this.universities = universities;
        this.specialities = specialities;
        this.institutes = institutes;
        this.chairs = chairs;
        this.groups = groups;
        this.courses = courses;
        this.lessons = lessons;
        this.schedules = schedules;
        this.students = students;
    }

    @Bean
    public CommandLineRunner insertNeoUniversities(NeoUniversityRepository repository,
                                                   PostgreStudentGroupRepository posStudentGroupRepository,
                                                   NeoInstituteRepository neoInstituteRepository,
                                                   NeoChairRepository neoChairRepository,
                                                   NeoCourseRepository neoCourseRepository,
                                                   NeoStudentGroupRepository neoStudentGroupRepository) {
        ArrayList<University> listUniversity = new ArrayList<>();

        log.info("Neo4j insert start");
        initSpecialities();
        initGroups(posStudentGroupRepository);

        for (var item : universities) {
            University university = new University(item.getId(), item.getUniversityName(), new HashSet<>());
            listUniversity.add(university);

            for (var inst : institutes) {
                if (Objects.equals(inst.getUniversity().getId(), item.getId())) {
                    HashSet<Chair> chairSet = new HashSet<>();
                    Institute institute = new Institute(inst.getId(), inst.getInstituteName(),
                            inst.getUniversity().getId(), chairSet);
                    university.getInstitutes().add(institute);

                    for (var ch : chairs) {
                        if (Objects.equals(inst.getId(), ch.getInstitute().getId())) {
                            HashSet<Speciality> specialitySet = new HashSet<>();
                            Chair chair = new Chair(ch.getId(), ch.getChairName(),
                                    ch.getChairCode(), specialitySet, new HashSet<>());
                            tempChairs.add(chair);

                            chairSet.add(chair);

                            for (var spec : ch.getSpecialityList()) {
                                for (var origSpec : tempSpecialities) {
                                    if (Objects.equals(spec.getId(), origSpec.getId())) {
                                        specialitySet.add(origSpec);
                                    }
                                }
                            }

                            for (var cour : courses) {
                                if (Objects.equals(ch.getId(), cour.getChair().getId())) {

                                    Course course = new Course(cour.getId(),
                                            cour.getCourseName(), Course.TypeCourse.valueOf(cour.getType().name()),
                                            cour.getStep(), cour.getDescription(),
                                            new HashSet<>(), new HashSet<>());
                                    var stG = cour.getStudentGroupList();

                                    chair.getCoursesList().add(course);

                                    for (var grp : stG) {
                                        for (var origSpec : tempGroups) {
                                            if (Objects.equals(grp.getId(), origSpec.getId())) {
                                                course.getGroupsList().add(origSpec);
                                                neoStudentGroupRepository.save(origSpec);
                                            }
                                        }
                                    }

                                    for (var les : lessons) {
                                        if (Objects.equals(cour.getId(), les.getCourse().getId())) {
                                            Lesson lesson = new Lesson(les.getId(), les.getLessonName(),
                                                    Lesson.TypeCourse.valueOf(les.getTypeCourse().name()));
                                            course.getLessonsList().add(lesson);
                                            tempLessons.add(lesson);
                                        }
                                    }
                                    neoCourseRepository.save(course);

                                }
                            }
                            neoChairRepository.save(chair);
                        }
                    }
                    neoInstituteRepository.save(institute);
                }
            }
            repository.save(university);
        }

        log.info("Neo4j insert end");
        return args -> {

        };
    }

    private void initSpecialities() {
        for (var origSpec : specialities) {
            Speciality speciality = new Speciality(origSpec.getId(),
                    origSpec.getSpecialityName(), origSpec.getSpecialityCode()
            );
            tempSpecialities.add(speciality);
        }

    }

    private void initGroups(PostgreStudentGroupRepository postgreStudentGroupRepository) {
        for (var item : groups) {
            StudentGroup studentGroup = new StudentGroup(item.getId(),
                    item.getGroupName(), item.getStep(), new HashSet<>(),
                    new HashSet<>());
            tempGroups.add(studentGroup);

            for (var student : students) {
                if (Objects.equals(student.getStudentGroup().getId(), item.getId())) {
                    Student st = new Student(student.getId(), student.getFullName(),
                            student.getStudentCode(), student.getBirthday());

                    studentGroup.getStudentsList().add(st);
                }
            }

            for (var sch : schedules) {
                if (Objects.equals(sch.getStudentGroup().getId(), item.getId())) {
                    Schedule schedule = new Schedule(sch.getId(), sch.getWeek(),
                            sch.getClassroom(), sch.getTeacher(), sch.getDate());

                    studentGroup.getSchedulesList().add(schedule);
                }
            }
        }
    }

    private void initStudents() {
        for (var item : students) {
            Student student = new Student(item.getId(), item.getFullName(),
                    item.getStudentCode(), item.getBirthday());
            tempStudents.add(student);
        }
    }

//    private void initCourses() {
//        for (var item : courses) {
//            Course course = new Course(item.getId(), item.getCourseName(),
//                    Course.TypeCourse.valueOf(item.getType().name()), item.getStep(),
//                    item.getDescription(), new HashSet<>());
//            tempCourses.add(course);
//        }
//    }

    private void initLessons() {
        for (var item : lessons) {
            Lesson lesson = new Lesson(item.getId(), item.getLessonName(),
                    Lesson.TypeCourse.valueOf(item.getTypeCourse().name()));
            tempLessons.add(lesson);
        }
    }

    private void initSchedules() {
        for (var item : schedules) {
            Schedule schedule = new Schedule(item.getId(), item.getWeek(),
                    item.getClassroom(), item.getTeacher(), item.getDate());
            tempSchedules.add(schedule);
        }
    }

}