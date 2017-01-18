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
import ua.org.hasper.service.*;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 12.01.2017.
 */
@Controller
public class HomeWorksController {

    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;

    @RequestMapping("admin/homeworks/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<HomeWork> homeWorks = homeWorkService.findAll();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        Teacher teacher = teacherService.findByLogin(user.getUsername());

        model.addAttribute("homeWorks", homeWorks);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("curentTeacher", teacher);

        return new ModelAndView("admin/homeworks/add");
    }

    @RequestMapping(value = "admin/homeworks/add", method = RequestMethod.POST)
    public ModelAndView newHomeWork(@RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_description") String description,
                                    Model model) {
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findById(teacherId);

        HomeWork homeWork = new HomeWork(new GregorianCalendar(), studentsGroup, teacher, subject, description);
        homeWorkService.addOrUpdateHomeWork(homeWork);

        model.addAttribute("addStatus", 1);

        return add(model);
    }


    @RequestMapping("admin/homeworks/list")
    public ModelAndView list(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<HomeWork> homeWorks = homeWorkService.findAll();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Teacher> teachers = teacherService.getAllTeachers();

        model.addAttribute("homeWorks",homeWorks);
        model.addAttribute("subjects", subjects);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("teachers", teachers);

        return new ModelAndView("admin/homeworks/list");
    }

    @RequestMapping("/homeWork/delete")
    public ModelAndView deleteSchedule(@RequestParam(value = "id") int id, Model model) {
        HomeWork homeWork = homeWorkService.findById(id);

        String message = homeWork.getStrDate();
        message+=" "+homeWork.getStudentsGroup().getName();
        message+=" "+homeWork.getSubject().getName();
        message+=" "+homeWork.getTeacher().getName()+" "+homeWork.getTeacher().getSurname();
        message+=" "+homeWork.getDescription();

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = homeWork.getHomeWorkStudentStatuses();

        for (HomeWorkStudentStatus hs:
             homeWorkStudentStatuses) {
            homeWorkStudentStatusService.delHomeWorkStudentStatus(hs);
        }

        homeWorkService.delHomeWork(homeWork);

        model.addAttribute("message", message);
        model.addAttribute("alert", 1);


        return list(model);

    }

    @RequestMapping("/homeWork/edit")
    public ModelAndView editTeacher(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_description") String description,
                                    Model model){
        HomeWork homeWork = homeWorkService.findById(id);

        Subject subject=subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Teacher teacher = teacherService.findById(teacherId);

        homeWork.setSubject(subject);
        homeWork.setStudentsGroup(studentsGroup);
        homeWork.setTeacher(teacher);
        homeWork.setDescription(description);

        String message = homeWork.getStrDate();
        message+=" "+homeWork.getStudentsGroup().getName();
        message+=" "+homeWork.getSubject().getName();
        message+=" "+homeWork.getTeacher().getName()+" "+homeWork.getTeacher().getSurname();
        message+=" "+homeWork.getDescription();

        model.addAttribute("message", message);
        model.addAttribute("alert", 2);


        return list(model);

    }
}