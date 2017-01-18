package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.service.GroupService;
import ua.org.hasper.service.ScheduleService;
import ua.org.hasper.service.StudentService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class SheduleController{

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ScheduleService scheduleService;

        @RequestMapping("/schedule")
        public String schedule(Model model) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("login", user.getUsername());


            StudentsGroup curStudentGroup = studentService.getStudentByLogin(user.getUsername()).getStudentsGroup();
            model.addAttribute("curStudentGroup", curStudentGroup.getName());
            List<StudentsGroup> studentsGroup = groupService.allGroupsWithout(curStudentGroup.getName());
            model.addAttribute("groupsname", studentsGroup);

            List<Schedule> monSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Monday, curStudentGroup);
            List<Schedule> tueSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Tuesday, curStudentGroup);
            List<Schedule> wedSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Wednesday, curStudentGroup);
            List<Schedule> thuSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Thursday, curStudentGroup);
            List<Schedule> friSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Friday, curStudentGroup);
            List<Schedule> satSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Saturday, curStudentGroup);

            model.addAttribute("monday", monSchedule);
            model.addAttribute("thuesday", tueSchedule);
            model.addAttribute("wednesday", wedSchedule);
            model.addAttribute("thurday", thuSchedule);
            model.addAttribute("friday", friSchedule);
            model.addAttribute("saturday", satSchedule);

            return "schedule";
        }

        @RequestMapping(value = "/schedule", method = RequestMethod.POST)
        public ModelAndView selectSchedule(@RequestParam(value = "class") String groupname,
                                           Model model) throws ParseException {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("login", user.getUsername());

            model.addAttribute("curStudentGroup", groupService.getGroupByName(groupname).getName());
            List<StudentsGroup> studentsGroup = groupService.allGroupsWithout(groupname);
            model.addAttribute("groupsname", studentsGroup);

            StudentsGroup curStudentGroup = groupService.getGroupByName(groupname);

            List<Schedule> monSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Monday, curStudentGroup);
            List<Schedule> tueSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Tuesday, curStudentGroup);
            List<Schedule> wedSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Wednesday, curStudentGroup);
            List<Schedule> thuSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Thursday, curStudentGroup);
            List<Schedule> friSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Friday, curStudentGroup);
            List<Schedule> satSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Saturday, curStudentGroup);

            model.addAttribute("monday", monSchedule);
            model.addAttribute("thuesday", tueSchedule);
            model.addAttribute("wednesday", wedSchedule);
            model.addAttribute("thurday", thuSchedule);
            model.addAttribute("friday", friSchedule);
            model.addAttribute("saturday", satSchedule);

            return new ModelAndView("schedule");

        }
}
