package com.multibase.inits;

import com.github.javafaker.Faker;
import com.multibase.entity.postgre.*;
import com.multibase.repository.postgre.*;
import lombok.extern.java.Log;
import org.slf4j.event.Level;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.InputStream;
import java.time.Instant;
import java.util.*;

@Configuration
@Log
public class Generator {
    private long count = 20;
    private ArrayList<University> universities;
    private ArrayList<Speciality> specialities;
    private ArrayList<Institute> institutes;
    private ArrayList<Chair> chairs;
    private ArrayList<StudentGroup> groups;
    private ArrayList<Course> courses;
    private ArrayList<Material> materials;
    private ArrayList<Lesson> lessons;
    private ArrayList<Schedule> schedules;
    private ArrayList<Visit> visits;
    private ArrayList<Student> students;

    public Generator(ArrayList<University> universities, ArrayList<Speciality> specialities,
                     ArrayList<Institute> institutes, ArrayList<Chair> chairs,
                     ArrayList<StudentGroup> groups, ArrayList<Course> courses,
                     ArrayList<Material> materials, ArrayList<Lesson> lessons,
                     ArrayList<Schedule> schedules, ArrayList<Visit> visits, ArrayList<Student> students) {
        this.universities = universities;
        this.specialities = specialities;
        this.institutes = institutes;
        this.chairs = chairs;
        this.groups = groups;
        this.courses = courses;
        this.materials = materials;
        this.lessons = lessons;
        this.schedules = schedules;
        this.visits = visits;
        this.students = students;
    }

    @Bean
    public CommandLineRunner generateUniversities(PostgreUniversityRepository repository) {
        log.info("Generator start");
        InputStream resourceStream = Generator.class.getClassLoader().getResourceAsStream("data/universities.txt");
        Scanner scanner = new Scanner(resourceStream);

        for (long i = 1; scanner.hasNextLine(); i++) {
            University university = new University(i, scanner.nextLine());
            universities.add(university);
        }
        repository.saveAll(universities);
        return args -> {

        };
    }

    @Bean
    @DependsOn("generateUniversities")
    public CommandLineRunner generateInstitutes(PostgreInstituteRepository repository) {
        Faker faker = new Faker();
        Random random = new Random();
        InputStream resourceStream = Generator.class.getClassLoader().getResourceAsStream("data/institutes.txt");
        Scanner scanner = new Scanner(resourceStream);

        ArrayList<String> institutesNames = new ArrayList<>();
        for (long i = 1; scanner.hasNextLine(); i++) {
            institutesNames.add(scanner.nextLine());
        }

        long counter = 1;

        for (long i = 0; i < universities.size(); i++) {
            for (int j = 0; j < random.nextInt(2, 5); j++) {
                int index = random.nextInt(0, institutesNames.size());
                Institute institute = new Institute(counter, institutesNames.get(index),
                        universities.get((int) i));
                institutes.add(institute);
                institutesNames.remove(index);
                counter++;
            }
        }
        repository.saveAll(institutes);
        return args -> {

        };
    }

    @Bean
    public CommandLineRunner generateSpecialities(PostgreSpecialityRepository repository) {
        InputStream resourceStream = Generator.class.getClassLoader().getResourceAsStream("data/specialities.txt");
        Scanner scanner = new Scanner(resourceStream);

        ArrayList<String> code = new ArrayList<>();
        ArrayList<String> specName = new ArrayList<>();
        for (long i = 1; scanner.hasNextLine(); i++) {
            String line = scanner.nextLine();
            code.add(line.split(":")[0]);
            specName.add(line.split(":")[1]);
        }

        for (int i = 1; i <= code.size(); i++) {
            Speciality speciality = new Speciality((long) i, code.get(i - 1), specName.get(i - 1));
            specialities.add(speciality);
        }
        repository.saveAll(specialities);
        return args -> {

        };
    }

    @Bean
    @DependsOn({"generateSpecialities", "generateInstitutes"})
    public CommandLineRunner generateChairs(PostgreChairRepository repository) {
        Random random = new Random();
        InputStream resourceStream = Generator.class.getClassLoader().getResourceAsStream("data/chairs.txt");
        Scanner scanner = new Scanner(resourceStream);

        ArrayList<String> chairNames = new ArrayList<>();
        for (long i = 1; scanner.hasNextLine(); i++) {
            chairNames.add(scanner.nextLine());
        }

        long counter = 1;
        for (long i = 1; i < institutes.size(); i++) {
            for (int j = 0; j < random.nextInt(2, 5); j++) {
                var list = new ArrayList<Speciality>();
                for (int z = 0; z < random.nextInt(2, 8); z++) {
                    list.add(specialities.get(random.nextInt(0, specialities.size())));
                }

                int index = random.nextInt(0, chairNames.size());
                Chair chair = new Chair(counter, chairNames.get(index), generateStudentCode(4),
                        institutes.get((int) i), list);
                chairs.add(chair);
                chairNames.remove(index);
                counter++;
            }
        }
        repository.saveAll(chairs);
        return args -> {

        };
    }

    @Bean
    @DependsOn("generateChairs")
    public CommandLineRunner generateStudentGroup(PostgreStudentGroupRepository repository) {
        Faker faker = new Faker();
        Random random = new Random();


        for (long i = 1; i <= count * 5; i++) {
            Chair chair = chairs.get(random.nextInt(0, chairs.size()));

            StringBuilder stringBuilder = new StringBuilder(generateStudentCode(8));
            stringBuilder.replace(4, 4, "-");
            stringBuilder.replace(7, 7, "-");

            StudentGroup studentGroup = new StudentGroup(
                    i,
                    stringBuilder.toString(),
                    (byte) random.nextInt(1, 7),
                    chair.getSpecialityList().get(random.nextInt(0, chair.getSpecialityList().size())),
                    chair);
            groups.add(studentGroup);
        }
        repository.saveAll(groups);

        return args -> {

        };
    }


    private String generateStudentCode(int len) {
        Random random = new Random();
        StringBuilder alph = new StringBuilder();

        for (int i = 0; i < 10; i++)
            alph.append((char)('0' + i));

        for (int i = 'А'; i <= 'Я'; i++) {
            alph.append((char)i);
        }

        StringBuilder code = new StringBuilder();
        for (int i = 0; i < len; i++) {
            code.append(alph.toString().charAt(random.nextInt(0, alph.length())));
        }

        return code.toString();
    }

    @Bean
    @DependsOn("generateStudentGroup")
    CommandLineRunner generateStudents(PostgreStudentRepository repository) {
        Faker faker = new Faker();
        Random random = new Random();

        for (long i = 1; i <= count * 30; i++) {
            Student student = new Student(i, faker.name().fullName(), generateStudentCode(7),
                    faker.date().birthday(),
                    groups.get(random.nextInt(0, groups.size())));
            students.add(student);
        }
        repository.saveAll(students);
        return args -> {

        };
    }

    @Bean
    @DependsOn("generateStudentGroup")
    CommandLineRunner generateCourse(PostgreCourseRepository repository) {
        Faker faker = new Faker();
        Random random = new Random();

        var array = Course.TypeCourse.values();

        for (long i = 1; i <= count * 10; i++) {
            ArrayList<StudentGroup> studentGroups = new ArrayList<>();
            for (int z = 0; z < random.nextInt(1, 6); z++) {
                studentGroups.add(groups.get(random.nextInt(0, groups.size())));
            }

            Course course = new Course(i, faker.educator().course(),
                    array[random.nextInt(0, array.length)],
                    (byte) random.nextInt(1, 5),
                    faker.lorem().characters(50, 100),
                    chairs.get(random.nextInt(0, chairs.size())),
                    studentGroups);
            courses.add(course);
        }
        repository.saveAll(courses);
        return args -> {

        };
    }

    @Bean
    CommandLineRunner generateMaterials(PostgreMaterialRepository materialRepository) {
        Faker faker = new Faker();
        Random random = new Random();

        for (long i = 1; i <= count * 20; i++) {
            Material material = new Material(i, faker.lorem().sentence(10,20));
            materials.add(material);
        }
        materialRepository.saveAll(materials);
        return args -> {

        };
    }

    @Bean
    @DependsOn({"generateMaterials", "generateCourse"})
    CommandLineRunner generateLessons(PostgreLessonRepository lessonRepository) {
        Faker faker = new Faker();
        Random random = new Random();

        var ar = Lesson.TypeCourse.values();

        for (long i = 1; i <= count * 20; i++) {
            Lesson lesson = new Lesson(i, faker.educator().course(),
                    ar[random.nextInt(0, ar.length)],
                    courses.get(random.nextInt(0, courses.size())),
                    materials.get(random.nextInt(0, materials.size())));
            lessons.add(lesson);
        }
        lessonRepository.saveAll(lessons);

        return args -> {

        };
    }

    @Bean
    @DependsOn({"generateStudentGroup", "generateLessons"})
    CommandLineRunner generateSchedule(PostgreScheduleRepository scheduleRepository) {
        Faker faker = new Faker();
        Random random = new Random();

        var dates = generateDates();

        for (long i = 1; i <= count * 20; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((char)random.nextInt('а',('я' + 1)));
            stringBuilder.append(faker.number().digits(3));

            Lesson lesson = lessons.get(random.nextInt(0, lessons.size()));
            StudentGroup group = lesson.getCourse().getStudentGroupList().get(random.nextInt(0,
                    lesson.getCourse().getStudentGroupList().size()));

            Schedule schedule = new Schedule(
                    i,
                    random.nextInt(1, 17),
                    stringBuilder.toString(),
                    faker.name().fullName(),
                    dates.get(random.nextInt(0, dates.size())),
                    group,
                    lesson);
            schedules.add(schedule);
        }
        scheduleRepository.saveAll(schedules);

        return args -> {

        };
    }

    @Bean
    @DependsOn({"generateSchedule", "generateStudents"})
    CommandLineRunner generateVisits(PostgreVisitRepository repository,
                                     PostgreStudentRepository studentRepository) {
        Faker faker = new Faker();
        Random random = new Random();

        long counter = 1;
        for (int i = 0; i < schedules.size(); i++) {
            for (var student :
                    studentRepository.findAllByStudentGroupId(schedules.get(i).getStudentGroup().getId())) {
                Visit visit = new Visit(counter, random.nextBoolean(),
                        student, schedules.get(i), schedules.get(i).getDate());
                visits.add(visit);
                counter++;
            }

        }
        repository.saveAll(visits);

        return args -> {

        };
    }



    private List<Date> generateDates() {
        var list = new ArrayList<Date>();

        Calendar start = new GregorianCalendar(2023, Calendar.SEPTEMBER, 1);
        Calendar end = new GregorianCalendar(2024, Calendar.MAY, 31);

        Calendar date = new GregorianCalendar();
        date.setTime(start.getTime());

        while (date.getTime().before(end.getTime())) {
            int hour = 9;
            int minute = 0;

            for (int i = 0; i < 6; i++) {
                date.set(Calendar.HOUR_OF_DAY, hour);
                date.set(Calendar.MINUTE, minute);

                Date generatedDate = date.getTime();
                if (generatedDate.after(Date.from(Instant.parse("2023-12-29T20:00:00Z"))) &&
                        generatedDate.before(Date.from(Instant.parse("2024-01-12T08:00:00Z"))))
                    continue;
                list.add(generatedDate);

                switch (i) {
                    case 0: minute = 30; break;
                    case 1: hour = 12; minute = 40; break;
                    case 2: hour = 14; minute = 20; break;
                    case 3: hour = 16; minute = 20; break;
                    case 4: hour = 18; minute = 0; break;
                }
            }

            date.add(Calendar.DAY_OF_MONTH, 1);
        }
        return list;
    }

}
