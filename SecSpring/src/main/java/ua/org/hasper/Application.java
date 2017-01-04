package ua.org.hasper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.*;
import ua.org.hasper.service.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final StudentService studentService,
                                  final GroupService groupService,
                                  final SubjectService subjectService,
                                  final TeacherService teacherService,
                                  final HomeWorkService homeWorkService,
                                  final ScheduleService scheduleService,
                                  final JurnalService jurnalService,
                                  final ScheduleTimesService scheduleTimesService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {

                //Пользователи
                userService.addUser(new CustomUser("admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN));
                userService.addUser(new CustomUser("petr", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.STUDENT));
                userService.addUser(new CustomUser("vanya", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.STUDENT));
                userService.addUser(new CustomUser("niko", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.STUDENT));
                userService.addUser(new CustomUser("mariya", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.TEACHER));
                userService.addUser(new CustomUser("olga", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.TEACHER));

                //Классы
                groupService.addGroup(new StudentsGroup("1-A"));
                groupService.addGroup(new StudentsGroup("2-Б"));

                //Студенты
                studentService.addStudent(new Student("Петр", "Иванов", new GregorianCalendar(1995, 3, 2), 672242435, "hasper@ukr.net", userService.getUserByLogin("petr"),groupService.getGroupByName("1-A")));
                studentService.addStudent(new Student("Ваня", "Никулин", new GregorianCalendar(1994, 6, 9), 935558899, "nik@mail.ru", userService.getUserByLogin("vanya"),groupService.getGroupByName("1-A")));
                studentService.addStudent(new Student("Николай", "Васильев", new GregorianCalendar(1994, 5, 9), 935558799, "nik@mail.ru", userService.getUserByLogin("niko"),groupService.getGroupByName("1-A")));



                //Учителя
                teacherService.addOrUpdateTeacher(new Teacher("Литвиненко","Мария",new GregorianCalendar(1981,5,9),936788222,"mariya@gmail.com", userService.getUserByLogin("mariya")));
                teacherService.addOrUpdateTeacher(new Teacher("Николаенко","Ольга",new GregorianCalendar(1985,6,22),674568745,"olga@gmail.com", userService.getUserByLogin("olga")));

                //Предметы
                subjectService.addOrUpdateSubject(new Subject("Математика"));
                subjectService.addOrUpdateSubject(new Subject("Английский"));
                subjectService.addOrUpdateSubject(new Subject("Информатика"));
                subjectService.addOrUpdateSubject(new Subject("Физкультура"));

                //Домашние задания
                homeWorkService.addOrUpdateHomeWork(new HomeWork(new GregorianCalendar(),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        subjectService.findByName("Математика"),
                        "Выполнить упражнение 8,9,10,15"));
                homeWorkService.addOrUpdateHomeWork(new HomeWork(new GregorianCalendar(),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("olga"),
                        subjectService.findByName("Английский"),
                        "Выполнить упражнение 56,99"));

                homeWorkService.addOrUpdateHomeWork(new HomeWork(new GregorianCalendar(),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        subjectService.findByName("Информатика"),
                        "Подготовка в контрольной работе"));
                homeWorkService.addOrUpdateHomeWork(new HomeWork(new GregorianCalendar(),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("olga"),
                        subjectService.findByName("Физкультура"),
                        "бег 1,5 км"));

                //Время уроков
                scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(1, new GregorianCalendar(0,0,0,9,0),45));
                scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(2, new GregorianCalendar(0,0,0,10,0),45));
                scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(3, new GregorianCalendar(0,0,0,11,0),45));
                scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(4, new GregorianCalendar(0,0,0,12,0),45));
                scheduleTimesService.addOrUpdateScheduleTimes(new ScheduleTimes(5, new GregorianCalendar(0,0,0,13,0),45));


                //Расписание уроков
                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Физкультура"),
                        teacherService.findByLogin("olga"),
                        groupService.getGroupByName("2-Б"),
                        WeekDayName.Monday,
                        scheduleTimesService.findByLessonNum(1)));

                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Информатика"),
                        teacherService.findByLogin("olga"),
                        groupService.getGroupByName("2-Б"),
                        WeekDayName.Monday,
                        scheduleTimesService.findByLessonNum(2)));

                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Английский"),
                        teacherService.findByLogin("olga"),
                        groupService.getGroupByName("2-Б"),
                        WeekDayName.Tuesday,
                        scheduleTimesService.findByLessonNum(1)));

                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Английский"),
                        teacherService.findByLogin("mariya"),
                        groupService.getGroupByName("1-A"),
                        WeekDayName.Monday,
                        scheduleTimesService.findByLessonNum(3)));

                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Информатика"),
                        teacherService.findByLogin("mariya"),
                        groupService.getGroupByName("1-A"),
                        WeekDayName.Monday,
                        scheduleTimesService.findByLessonNum(4)));

                scheduleService.addOrUpdateSchedule(new Schedule(subjectService.findByName("Информатика"),
                        teacherService.findByLogin("mariya"),
                        groupService.getGroupByName("1-A"),
                        WeekDayName.Tuesday,
                        scheduleTimesService.findByLessonNum(4)));

                //Журнал оценок и посещений
                jurnalService.addOrUpdateJurnal(new Jurnal (
                        subjectService.findByName("Английский"),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        new MarkStamp(Mark.m10,MarkType.THEMATIC, new GregorianCalendar(2016,9,19)),
                        studentService.getStudentByLogin("petr")));

                jurnalService.addOrUpdateJurnal(new Jurnal (
                        subjectService.findByName("Английский"),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        new MarkStamp(Mark.m10,MarkType.THEMATIC, new GregorianCalendar(2016,9,18)),
                        studentService.getStudentByLogin("petr")));

                jurnalService.addOrUpdateJurnal(new Jurnal (
                        subjectService.findByName("Английский"),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        new MarkStamp(Mark.m10,MarkType.THEMATIC, new GregorianCalendar(2016,9,20)),
                        studentService.getStudentByLogin("petr")));

                jurnalService.addOrUpdateJurnal(new Jurnal (
                        subjectService.findByName("Информатика"),
                        groupService.getGroupByName("1-A"),
                        teacherService.findByLogin("mariya"),
                        new MarkStamp(Mark.m10,MarkType.THEMATIC, new GregorianCalendar(2016,9,21)),
                        studentService.getStudentByLogin("petr")));

                jurnalService.addOrUpdateJurnal(new Jurnal (
                        subjectService.findByName("Английский"),
                        groupService.getGroupByName("2-Б"),
                        teacherService.findByLogin("olga"),
                        new MarkStamp(Mark.m7,MarkType.CONTROL),
                        studentService.getStudentByLogin("petr")));




            }
        };
    }
}