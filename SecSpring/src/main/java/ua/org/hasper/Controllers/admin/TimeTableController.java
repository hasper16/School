package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.*;
import ua.org.hasper.service.*;

import java.util.List;

@Controller
public class TimeTableController {
    public static final int PAGESIZE = 25;
    @Autowired
    private GroupService groupService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ScheduleTimesService scheduleTimesService;
    @Autowired
    private ScheduleService scheduleService;
    private List<Schedule> schedules;
    private WeekDayName weekDayName;
    private int maxCountSchedule = 0;

    @RequestMapping("admin/timetable/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<ScheduleTimes> scheduleTimes = scheduleTimesService.getAll();


        model.addAttribute("groups", studentsGroups);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("times", scheduleTimes);
        model.addAttribute("weekdays", WeekDayName.values());

        return new ModelAndView("admin/timetable/add");
    }

    @RequestMapping(value = "admin/timetable/add", method = RequestMethod.POST)
    public ModelAndView newSubject(@RequestParam(value = "j_group") int groupId,
                                   @RequestParam(value = "j_subject") int subjectId,
                                   @RequestParam(value = "j_teacher") int teacherId,
                                   @RequestParam(value = "j_weekday") String weekday,
                                   @RequestParam(value = "j_time") int timeId,
                                   Model model) {

        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findById(teacherId);
        WeekDayName weekDayName = WeekDayName.getDayByName(weekday);
        ScheduleTimes scheduleTimes = scheduleTimesService.findById(timeId);

        int alert = 0;
        String message = "";

        try {
            for (Schedule sh :
                    scheduleService.findAll()) {
                if (sh.getStudentsGroup().equals(studentsGroup) &&
                        sh.getSubject().equals(subject) &&
                        sh.getTeacher().equals(teacher) &&
                        sh.getWeekDayName().equals(weekDayName) &&
                        sh.getScheduleTimes().equals(scheduleTimes)) {
                    alert = 2;
                    message = "Такое расписание уже существует";
                } else {
                    Schedule schedule = new Schedule(subject, teacher, studentsGroup, weekDayName, scheduleTimes);
                    scheduleService.addOrUpdateSchedule(schedule);
                    alert = 1;
                    message = "Расписание " + schedule.getScheduleTimes().getLessonNum() + ") " + schedule.getScheduleTimes().getSdt() + " "
                            + schedule.getWeekDayName().toString() + " "
                            + schedule.getSubject().getName() + " "
                            + schedule.getStudentsGroup().getName() + " "
                            + schedule.getTeacher().getName() + " " + schedule.getTeacher().getSurname() + " успешно создано";
                }
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return add(model);
    }

    @RequestMapping("admin/timetable/list")
    public ModelAndView list(Model model) {
        return filterList(WeekDayName.Monday, 0, model);
    }

    public ModelAndView pageElements(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("weekDayName", WeekDayName.values());
        List<Subject> subjects = subjectService.getAllSubjects();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<ScheduleTimes> scheduleTimes = scheduleTimesService.getAll();

        model.addAttribute("subjects", subjects);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("teachers", teachers);
        model.addAttribute("times", scheduleTimes);
        model.addAttribute("weekdays", WeekDayName.values());
        return new ModelAndView("admin/timetable/list");
    }

    @RequestMapping("/schedule/")
    public ModelAndView filterList(@RequestParam(value = "weekDay") WeekDayName weekDay,
                                   @RequestParam(value = "page") int page,
                                   Model model) {
        weekDayName = weekDay;
        schedules = scheduleService.findByWeekDayName(weekDayName, page, PAGESIZE);
        maxCountSchedule = scheduleService.findByWeekDayName(weekDayName).size() / PAGESIZE;
        model.addAttribute("schedules", schedules);
        model.addAttribute("maxCountScheduleRows", maxCountSchedule);
        model.addAttribute("curWeekDay", weekDayName);
        model.addAttribute("curPage", page);

        return pageElements(model);

    }

    @RequestMapping("/schedule/delete")
    public ModelAndView deleteSchedule(@RequestParam(value = "id") int id, Model model) {
        int alert;
        String message;
        try {
            Schedule schedule = scheduleService.findById(id);
            scheduleService.delSchedule(schedule);
            message = "Расписание " + schedule.getScheduleTimes().getLessonNum() + ") " + schedule.getScheduleTimes().getSdt() + " "
                    + schedule.getWeekDayName().toString() + " "
                    + schedule.getSubject().getName() + " "
                    + schedule.getStudentsGroup().getName() + " "
                    + schedule.getTeacher().getName() + " " + schedule.getTeacher().getSurname() + " успешно удалено";
            alert = 1;
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return list(model);

    }

    @RequestMapping("/schedule/edit")
    public ModelAndView editTeacher(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_lessonNum") int lessonNumId,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_duration") int durationMin,
                                    @RequestParam(value = "j_weekday") String weekday,
                                    Model model) {
        Schedule schedule = scheduleService.findById(id);
        Subject subject = subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Teacher teacher = teacherService.findById(teacherId);
        ScheduleTimes scheduleTimes = scheduleTimesService.findById(lessonNumId);

        int alert = 0;
        String message = "";

        try {
            for (Schedule sh :
                    scheduleService.findAll()) {
                if (sh.getStudentsGroup().equals(studentsGroup) &&
                        sh.getSubject().equals(subject) &&
                        sh.getTeacher().equals(teacher) &&
                        sh.getWeekDayName().equals(weekDayName) &&
                        sh.getScheduleTimes().equals(scheduleTimes) &&
                        sh.getId() != id) {
                    alert = 2;
                    message = "Такое расписание уже существует";
                }
            }
            if (alert != 2) {
                schedule.setSubject(subject);
                schedule.setTeacher(teacher);
                schedule.setStudentsGroup(studentsGroup);
                schedule.setWeekDayName(weekDayName);
                schedule.setScheduleTimes(scheduleTimes);
                scheduleService.addOrUpdateSchedule(schedule);
                alert = 1;
                message = "Расписание " + schedule.getScheduleTimes().getLessonNum() + ") " + schedule.getScheduleTimes().getSdt() + " "
                        + schedule.getWeekDayName().toString() + " "
                        + schedule.getSubject().getName() + " "
                        + schedule.getStudentsGroup().getName() + " "
                        + schedule.getTeacher().getName() + " " + schedule.getTeacher().getSurname() + " успешно изменено";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);


        return list(model);

    }
}