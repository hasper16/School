package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.service.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 14.01.2017.
 */
@Controller
public class JurnalsController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private MarkStampService markStampService;

    private int PAGESIZE = 25;
    int curPage = 1;
    MarkType curMarkType = MarkType.THEMATIC;


    @RequestMapping("admin/journal/addMark")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<Student> students = studentService.getAllStudents();

        Teacher teacher = teacherService.findByLogin(user.getUsername());

        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("groups", studentsGroups);
        model.addAttribute("students", students);
        model.addAttribute("curentTeacher", teacher);
        model.addAttribute("types", MarkType.values());
        model.addAttribute("marks", Mark.values());


        return new ModelAndView("admin/journal/addMark");
    }

    @RequestMapping(value = "admin/journal/addMark", method = RequestMethod.POST)
    public ModelAndView newMark(@RequestParam(value = "j_subject") int subjectId,
                                @RequestParam(value = "j_teacher") int teacherId,
                                @RequestParam(value = "j_student") int studentId,
                                @RequestParam(value = "j_type") String markType,
                                @RequestParam(value = "j_mark") int mark,
                                Model model) {
        int alert = 0;
        String message = "";
        try {
            Subject subject = subjectService.findById(subjectId);
            Teacher teacher = teacherService.findById(teacherId);
            Student student = studentService.getStudentById(studentId);
            List<MarkStamp> markDublicat = markStampService.findByTypeAndStudentAndTeacherAndSubjectAndMark(MarkType.markTypeByName(markType), student, teacher, subject, Mark.MarkByNumMark(mark));

            Calendar calendar = new GregorianCalendar();
            for (MarkStamp m :
                    markDublicat) {
                if (m.getDate().get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) &&
                        m.getDate().get(Calendar.MONTH) == calendar.get(Calendar.MONTH) &&
                        m.getDate().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                    alert = 2;
                    message = "Такая оценка за этот день уже существует";
                }
            }

            if (alert != 2) {
                MarkStamp markStamp = new MarkStamp(Mark.MarkByNumMark(mark), MarkType.markTypeByName(markType));
                Jurnal jurnal = new Jurnal(subject, teacher, markStamp, student);

                jurnalService.addOrUpdateJurnal(jurnal);
                alert = 1;
                message = "Оценка " + markStamp.getMark().toString() + " " + markStamp.getDateString();
                message += " " + markStamp.getJurnal().getStudent().getName();
                message += " " + markStamp.getJurnal().getStudent().getSurname();
                message += " " + markStamp.getJurnal().getSubject().getName();
                message += " " + markStamp.getJurnal().getTeacher().getName() + " " + markStamp.getJurnal().getTeacher().getSurname() + " успешно добавлена";
            }

        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }


        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return add(model);
    }

    @RequestMapping("admin/journal/journalList")
    public ModelAndView groupList(Model model) {

        return groupListFilter(0, MarkType.THEMATIC, 0, model);
    }

    @RequestMapping(value = "/filter", params = {"group", "type", "page"})
    public ModelAndView groupListFilter(@RequestParam(value = "group") int groupId,
                                        @RequestParam(value = "type") MarkType type,
                                        @RequestParam(value = "page") int page,
                                        Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();
        List<MarkStamp> markStamps = new LinkedList<>();
        curPage = page;
        int totalPages = 0;
        curMarkType = type;
        if (groupId == 0) {
            Page<MarkStamp> stampPage = markStampService.findByMarkType(curMarkType, page, PAGESIZE);
            totalPages = stampPage.getTotalPages();
            markStamps = stampPage.getContent();
        } else {
            StudentsGroup studentsGroup = groupService.getGroupById(groupId);
            Page<MarkStamp> stampPage = markStampService.findByGroupAndMarkType(studentsGroup, curMarkType, page, PAGESIZE);
            totalPages = stampPage.getTotalPages();
            markStamps = stampPage.getContent();
        }

        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();


        model.addAttribute("groups", studentsGroups);
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("markTypes", MarkType.values());
        model.addAttribute("marks", Mark.values());
        model.addAttribute("markStamps", markStamps);
        model.addAttribute("selectGroup", groupId);
        model.addAttribute("type", curMarkType);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages - 1);
        return new ModelAndView("admin/journal/journalList");
    }

    @RequestMapping("/journalList/delete")
    public ModelAndView delete(@RequestParam(value = "id") int id, Model model) {
        int alert = 0;
        String message = "";
        try {
            MarkStamp markStamp = markStampService.findById(id);
            markStampService.delMarkStamp(markStamp);
            alert = 1;
            message = "Оценка " + markStamp.getMark().toString() + " " + markStamp.getDateString();
            message += " " + markStamp.getJurnal().getStudent().getName();
            message += " " + markStamp.getJurnal().getStudent().getSurname();
            message += " " + markStamp.getJurnal().getSubject().getName();
            message += " " + markStamp.getJurnal().getTeacher().getName() + " " + markStamp.getJurnal().getTeacher().getSurname() + " успешно удалена";
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);
        return groupList(model);
    }

    @RequestMapping("/journalList/edit")
    public ModelAndView editMark(@RequestParam(value = "id") int id,
                                 @RequestParam(value = "j_student") int studentId,
                                 @RequestParam(value = "j_subject") int subjectId,
                                 @RequestParam(value = "j_teacher") int teacherId,
                                 @RequestParam(value = "j_markType") String markType,
                                 @RequestParam(value = "j_mark") int mark,
                                 Model model) {
        int alert = 0;
        String message = "";
        MarkStamp markStamp = markStampService.findById(id);
        Student student = studentService.getStudentById(studentId);
        Subject subject = subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(student.getStudentsGroup().getId());
        Teacher teacher = teacherService.findById(teacherId);
        try {
            List<MarkStamp> markDublicat = markStampService.findByTypeAndStudentAndTeacherAndSubjectAndMark(MarkType.markTypeByName(markType), student, teacher, subject, Mark.MarkByNumMark(mark));

            Calendar calendar = new GregorianCalendar();
            for (MarkStamp m :
                    markDublicat) {
                if (m.getDate().get(Calendar.DAY_OF_MONTH) == markStamp.getDate().get(Calendar.DAY_OF_MONTH) &&
                        m.getDate().get(Calendar.MONTH) == markStamp.getDate().get(Calendar.MONTH) &&
                        m.getDate().get(Calendar.YEAR) == markStamp.getDate().get(Calendar.YEAR) &&
                        m.getId() != markStamp.getId()) {
                    alert = 2;
                    message = "Такая оценка за этот день уже существует";
                }
            }
            if (alert != 2) {
                Jurnal jurnal = markStamp.getJurnal();

                jurnal.setStudent(student);
                jurnal.setSubject(subject);
                jurnal.setTeacher(teacher);
                markStamp.setJurnal(jurnal);
                markStamp.setMark(Mark.MarkByNumMark(mark));
                markStamp.setMarkType(MarkType.markTypeByName(markType));
                markStampService.addOrUpdateMarkStamp(markStamp);

                alert = 1;
                message = "Оценка " + markStamp.getMark().toString() + " " + markStamp.getDateString();
                message += " " + markStamp.getJurnal().getStudent().getName();
                message += " " + markStamp.getJurnal().getStudent().getSurname();
                message += " " + markStamp.getJurnal().getSubject().getName();
                message += " " + markStamp.getJurnal().getTeacher().getName() + " " + markStamp.getJurnal().getTeacher().getSurname() + " успешно изменена";
            }

        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return groupListFilter(studentsGroup.getId(), curMarkType, curPage, model);

    }
}
