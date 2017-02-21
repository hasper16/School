package ua.org.hasper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Photo;
import ua.org.hasper.myTest.Test;
import ua.org.hasper.service.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final GroupService groupService,
                                  final StudentService studentService,
                                  final UserService userService,
                                  final TeacherService teacherService,
                                  final SubjectService subjectService,
                                  final ScheduleService scheduleService,
                                  final ScheduleTimesService scheduleTimesService,
                                  final JurnalService jurnalService,
                                  final MarkStampService markStampService,
                                  final HomeWorkService homeWorkService,
                                  final HomeWorkStudentStatusService homeWorkStudentStatusService,
                                  final PhotoService photoService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                if (userService.getUserByLogin("admin") == null) {
                    File file = new File("src/main/webapp/images/default_avatar.png"); //"images/default_avatar.png"
                    FileInputStream fileInputStream = new FileInputStream(file);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    byte[] image = new byte[bufferedInputStream.available()];
                    bufferedInputStream.read(image, 0, bufferedInputStream.available());
                    Photo default_photo = new Photo("default_avatar.png", image);
                    photoService.add(default_photo);

                    Test test = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, null);

                    test.addGroups();
                    test.addStudents(220);
                    test.addTeachers(48);
                    test.addSubjects();
                    test.addScheduleTimes();
                    test.addSchedules(5);
                    test.addUser("admin", UserRole.ROLE_ADMIN);
                    test.addStudent("Павел", "Еременко", "student");
                    test.addTeacher("Мария", "Плюшкина", "teacher");


                    Test monday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Monday);
                    Test tuesday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Tuesday);
                    Test wednesday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Wednesday);
                    Test thursday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Thursday);
                    Test friday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Friday);
                    Test saturday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Saturday);
                    Test sunday = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, WeekDayName.Sunday);
                    monday.start();
                    tuesday.start();
                    wednesday.start();
                    thursday.start();
                    friday.start();
                    saturday.start();
                    sunday.start();
                    Thread.sleep(86400000);
                }
                while (1 < 2) {
                    Test realTimeTest = new Test(groupService, studentService, userService,
                            teacherService, subjectService, scheduleTimesService, scheduleService,
                            jurnalService, homeWorkService, photoService, null);

                    realTimeTest.start();
                    Thread.sleep(86400000);

                }
            }
        };
    }
}