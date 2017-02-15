package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.GrantedAuthority;
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
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.service.GroupService;
import ua.org.hasper.service.ScheduleService;
import ua.org.hasper.service.StudentService;

import java.util.Collection;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class SheduleController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/schedule")
    public ModelAndView schedule(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }
        }

        List<StudentsGroup> studentsGroup = groupService.getAllGroups();
        model.addAttribute("groupsname", studentsGroup);

        Student student = studentService.getStudentByLogin(user.getUsername());
        if (student != null) {
            return list(student.getStudentsGroup().getId(), model);
        } else return new ModelAndView("schedule");

    }

    public ModelAndView list(int groupId, Model model) {
        StudentsGroup curStudentGroup = groupService.getGroupById(groupId);
        model.addAttribute("curStudentGroup", curStudentGroup);
        List<Schedule> monSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Monday, curStudentGroup);
        List<Schedule> tueSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Tuesday, curStudentGroup);
        List<Schedule> wedSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Wednesday, curStudentGroup);
        List<Schedule> thuSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Thursday, curStudentGroup);
        List<Schedule> friSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Friday, curStudentGroup);
        List<Schedule> satSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Saturday, curStudentGroup);
        List<Schedule> sunSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Sunday, curStudentGroup);

        model.addAttribute("monday", monSchedule);
        model.addAttribute("thuesday", tueSchedule);
        model.addAttribute("wednesday", wedSchedule);
        model.addAttribute("thurday", thuSchedule);
        model.addAttribute("friday", friSchedule);
        model.addAttribute("saturday", satSchedule);
        model.addAttribute("sunday", sunSchedule);

        return new ModelAndView("schedule");
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public ModelAndView selectSchedule(@RequestParam(value = "class") int groupId,
                                       Model model) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }
        }
        List<StudentsGroup> studentsGroup = groupService.getAllGroups();
        model.addAttribute("groupsname", studentsGroup);

        return list(groupId, model);

    }
}
