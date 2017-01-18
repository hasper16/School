package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.*;
import ua.org.hasper.service.*;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 11.01.2017.
 */
@Controller
public class GroupsController {
    @Autowired
    GroupService groupService;
    @Autowired
    HomeWorkService homeWorkService;
    @Autowired
    JurnalService jurnalService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    StudentService studentService;


    @RequestMapping("admin/groups/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/groups/add");
    }

    @RequestMapping(value = "admin/groups/add", method = RequestMethod.POST)
    public ModelAndView newGroup(@RequestParam(value = "j_group") String group,
                                 Model model)  {
        StudentsGroup studentsGroup = new StudentsGroup(group);
        groupService.addGroup(studentsGroup);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("addStatus", 1);

        return new ModelAndView("admin/groups/add");
    }

    @RequestMapping("admin/groups/list")
    public void list(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<StudentsGroup> studentsGroups = groupService.getAllGroups();


        model.addAttribute("groups",studentsGroups);

    }

    @RequestMapping("/groups/delete")
    public ModelAndView deleteGroup(@RequestParam(value="id") int id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        String message ;
        StudentsGroup studentsGroup = groupService.getGroupById(id);
        List<HomeWork>homeWorks = homeWorkService.findByGroup(studentsGroup);
        List<Jurnal>jurnals=jurnalService.findByGroup(studentsGroup);
        List<Schedule>schedules=scheduleService.findByGroup(studentsGroup);
        List<Student>students=studentService.getStudentByGroup(studentsGroup);

        for (HomeWork h:
             homeWorks) {
            h.setStudentsGroup(null);
            homeWorkService.addOrUpdateHomeWork(h);
        }

        for (Jurnal j:
             jurnals) {
            j.setStudentsGroup(null);
            jurnalService.addOrUpdateJurnal(j);
        }

        for (Schedule s:
             schedules) {
            s.setStudentsGroup(null);
            scheduleService.addOrUpdateSchedule(s);
        }

        for (Student s:
             students) {
            s.setStudentsGroup(null);
            studentService.addStudent(s);
        }


        message=studentsGroup.getName();
        groupService.delGroup(studentsGroup);

        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        model.addAttribute("groups",studentsGroups);


        model.addAttribute("message",message);
        model.addAttribute("alert",1);

        return new ModelAndView("admin/groups/list");

    }


    @RequestMapping("/groups/edit")
    public ModelAndView editGroup(@RequestParam(value="id") int id,
                                    @RequestParam(value = "j_group") String group,
                                    Model model)  {
        StudentsGroup studentsGroup=groupService.getGroupById(id);
        studentsGroup.setName(group);

        groupService.editGroup(studentsGroup);
        String message ;
        message=studentsGroup.getName();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        model.addAttribute("message",message);
        model.addAttribute("alert",2);

        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        model.addAttribute("groups",studentsGroups);

        return new ModelAndView("admin/groups/list");

    }

}
