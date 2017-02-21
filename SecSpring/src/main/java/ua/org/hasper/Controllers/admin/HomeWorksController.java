package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.Entity.Teacher;
import ua.org.hasper.service.*;

import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class HomeWorksController {

    public static final int PAGESIZE = 25;
    int curPage = 0;
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
        int alert = 0;
        String message = "";
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findById(teacherId);
        try {
            List<HomeWork> worksDublicat = homeWorkService.findAll();
            for (HomeWork work :
                    worksDublicat) {
                if (work.getStudentsGroup().equals(studentsGroup) &&
                        work.getSubject().equals(subject) &&
                        work.getTeacher().equals(teacher) &&
                        work.getDescription().equals(description)) {
                    alert = 2;
                    message = "Такое задание уже существует";
                }
            }
            if (alert != 2) {
                HomeWork homeWork = new HomeWork(new GregorianCalendar(), studentsGroup, teacher, subject, description);
                homeWorkService.addOrUpdateHomeWork(homeWork);
                alert = 1;
                message = "Задание " + homeWork.getStudentsGroup().getName()
                        + " " + homeWork.getSubject().getName()
                        + " " + homeWork.getTeacher().getName()
                        + " " + homeWork.getTeacher().getSurname()
                        + " " + homeWork.getDescription().substring(0, 10)
                        + "... успешно добавлено";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return add(model);
    }


    @RequestMapping("admin/homeworks/list")
    public ModelAndView list(Model model) {
        return listPage(0, model);
    }

    @RequestMapping(value = "admin/homeworks/list", params = {"page"})
    public ModelAndView listPage(@RequestParam(value = "page") int page, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<HomeWork> homeWorkPage = homeWorkService.findAll(page, PAGESIZE);
        curPage = page;
        int totalPages = homeWorkPage.getTotalPages() - 1;
        List<HomeWork> homeWorks = homeWorkPage.getContent();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Teacher> teachers = teacherService.getAllTeachers();

        model.addAttribute("homeWorks", homeWorks);
        model.addAttribute("subjects", subjects);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("teachers", teachers);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return new ModelAndView("admin/homeworks/list");
    }

    @RequestMapping("/homeWork/delete")
    public ModelAndView deleteSchedule(@RequestParam(value = "id") int id, Model model) {
        int alert;
        String message;
        HomeWork homeWork = homeWorkService.findById(id);
        try {
            homeWorkService.delHomeWork(homeWork);
            alert = 1;
            message = "Задание " + homeWork.getStudentsGroup().getName()
                    + " " + homeWork.getSubject().getName()
                    + " " + homeWork.getTeacher().getName()
                    + " " + homeWork.getTeacher().getSurname()
                    + " " + homeWork.getDescription().substring(0, 10)
                    + "... успешно удалено";
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }


        model.addAttribute("message", message);
        model.addAttribute("alert", alert);


        return listPage(curPage, model);

    }

    @RequestMapping("/homeWork/edit")
    public ModelAndView editTeacher(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_description") String description,
                                    Model model) {
        int alert = 0;
        String message = "";
        HomeWork homeWork = homeWorkService.findById(id);

        Subject subject = subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Teacher teacher = teacherService.findById(teacherId);

        try {
            List<HomeWork> worksDublicat = homeWorkService.findAll();
            for (HomeWork work :
                    worksDublicat) {
                if (work.getStudentsGroup().equals(studentsGroup) &&
                        work.getSubject().equals(subject) &&
                        work.getTeacher().equals(teacher) &&
                        work.getDescription().equals(description) &&
                        work.getId() != homeWork.getId()) {
                    alert = 2;
                    message = "Такое задание уже существует";
                }
            }
            if (alert != 2) {
                homeWork.setSubject(subject);
                homeWork.setStudentsGroup(studentsGroup);
                homeWork.setTeacher(teacher);
                homeWork.setDescription(description);
                homeWorkService.addOrUpdateHomeWork(homeWork);
                alert = 1;
                message = "Задание " + homeWork.getStudentsGroup().getName()
                        + " " + homeWork.getSubject().getName()
                        + " " + homeWork.getTeacher().getName()
                        + " " + homeWork.getTeacher().getSurname()
                        + " " + homeWork.getDescription().substring(0, 10)
                        + "... успешно изменено";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return listPage(curPage, model);

    }
}