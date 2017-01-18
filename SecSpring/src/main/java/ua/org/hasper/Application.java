package ua.org.hasper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.*;
import ua.org.hasper.service.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

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
                                  final ScheduleTimesService scheduleTimesService,
                                  final ScheduleService scheduleService,
                                  final JurnalService jurnalService,
                                  final MarkStampService markStampService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
               Test test = new Test(groupService,
                       studentService,
                       userService,
                       teacherService,
                       subjectService,
                       scheduleTimesService,
                       scheduleService,
                       jurnalService,
                       markStampService);
                test.mainTest();
            }
        };
    }
}