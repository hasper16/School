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

               /* File file = new File("src/main/webapp/images/default_avatar.png");
                FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                byte []image = new byte[bufferedInputStream.available()];
                bufferedInputStream.read(image,0,bufferedInputStream.available());

                Photo default_photo = new Photo("default_avatar.png",image);
                photoService.add(default_photo);*/

               /*Test test = new Test(groupService,studentService,userService,
                       teacherService,subjectService,scheduleTimesService,scheduleService,
                       jurnalService,markStampService,homeWorkService,homeWorkStudentStatusService,photoService);*/

       /*         test.addGroups();
                test.addStudents(480);
                test.addTeachers(48);
                test.addSubjects();
                test.addScheduleTimes();
                test.addSchedules(4);
                test.addUser("admin", UserRole.ROLE_ADMIN);
                test.addStudent("Павел","Еременко","student");*/

                /*Test sundayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Sunday);
                Test mondayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Monday);
                Test tuesdayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Tuesday);
                Test wendnesdayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Wednesday);
                Test thursdayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Thursday);
                Test fridayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Friday);
                Test saturdayTest = new Test(groupService, studentService, userService,
                        teacherService, subjectService, scheduleTimesService, scheduleService,
                        jurnalService, markStampService, homeWorkService, homeWorkStudentStatusService, photoService, WeekDayName.Saturday);

                sundayTest.start();
                mondayTest.start();
                tuesdayTest.start();
                wendnesdayTest.start();
                thursdayTest.start();
                fridayTest.start();
                saturdayTest.start();*/

            }
        };
    }
}