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
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.service.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Pavel.Eremenko on 11.01.2017.
 */
@Controller
public class TimeTableController {
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
        WeekDayName weekDayName = WeekDayName.valueOf(weekday);
        ScheduleTimes scheduleTimes = scheduleTimesService.findById(timeId);

        Schedule schedule = new Schedule(subject, teacher, studentsGroup, weekDayName, scheduleTimes);

        scheduleService.addOrUpdateSchedule(schedule);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("addStatus", 1);

        return new ModelAndView("admin/timetable/add");
    }

    @RequestMapping("admin/timetable/list")
    public void list(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<Schedule> schedules1 = scheduleService.findByWeekDayName(WeekDayName.Monday);
        List<Schedule> schedules2 = scheduleService.findByWeekDayName(WeekDayName.Tuesday);
        List<Schedule> schedules3 = scheduleService.findByWeekDayName(WeekDayName.Wednesday);
        List<Schedule> schedules4 = scheduleService.findByWeekDayName(WeekDayName.Thursday);
        List<Schedule> schedules5 = scheduleService.findByWeekDayName(WeekDayName.Friday);
        List<Schedule> schedules6 = scheduleService.findByWeekDayName(WeekDayName.Saturday);
        List<Schedule> schedules7 = scheduleService.findByWeekDayName(WeekDayName.Wednesday);

        model.addAttribute("schedule1", schedules1);
        model.addAttribute("schedule2", schedules2);
        model.addAttribute("schedule3", schedules3);
        model.addAttribute("schedule4", schedules4);
        model.addAttribute("schedule5", schedules5);
        model.addAttribute("schedule6", schedules6);
        model.addAttribute("schedule7", schedules7);

        List<Subject> subjects = subjectService.getAllSubjects();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Teacher> teachers = teacherService.getAllTeachers();

        model.addAttribute("subjects", subjects);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("teachers", teachers);

    }


    @RequestMapping("/schedule/delete")
    public ModelAndView deleteSchedule(@RequestParam(value = "id") int id, Model model) {
        Schedule schedule = scheduleService.findById(id);
        String message = schedule.getWeekDayName().name()
                + " " + schedule.getStudentsGroup().getName() + " "
                + schedule.getSubject().getName() + " "
                + schedule.getScheduleTimes().getSdt();
        scheduleService.delSchedule(schedule);


        model.addAttribute("message", message);
        model.addAttribute("alert", 1);

        list(model);
        return new ModelAndView("admin/timetable/list");

    }

    @RequestMapping("/schedule/edit")
    public ModelAndView editTeacher(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_lessonNum") int lessonNum,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_duration") int durationMin,
                                    Model model){
Schedule schedule=scheduleService.findById(id);

        Subject subject=subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Teacher teacher = teacherService.findById(teacherId);

        schedule.getScheduleTimes().setLessonNum(lessonNum);
        schedule.setSubject(subject);
        schedule.setStudentsGroup(studentsGroup);
        schedule.setTeacher(teacher);
        schedule.getScheduleTimes().setDurationMin(durationMin);

        String message = schedule.getWeekDayName().name()
                + " " + schedule.getStudentsGroup().getName() + " "
                + schedule.getSubject().getName() + " "
                + schedule.getScheduleTimes().getSdt();
        model.addAttribute("message", message);
        model.addAttribute("alert", 2);

        list(model);

        return new ModelAndView("admin/timetable/list");

    }
}