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
import ua.org.hasper.Entity.Enums.Mark;
import ua.org.hasper.Entity.Enums.MarkType;
import ua.org.hasper.service.*;

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
    public ModelAndView newMark(@RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_student") int studentId,
                                    @RequestParam(value = "j_type") String markType,
                                    @RequestParam(value = "j_mark") int mark,
                                    Model model) {
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Subject subject = subjectService.findById(subjectId);
        Teacher teacher = teacherService.findById(teacherId);
        Student student = studentService.getStudentById(studentId);

        MarkStamp markStamp = new MarkStamp(Mark.MarkFromIntValue(mark), MarkType.markTypeFromString(markType));
        Jurnal jurnal = new Jurnal(subject, studentsGroup, teacher, markStamp, student);

        jurnalService.addOrUpdateJurnal(jurnal);

        model.addAttribute("addStatus", 1);

        return add(model);
    }

    @RequestMapping("admin/journal/journalList")
    public ModelAndView groupList (Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<StudentsGroup> studentsGroups=groupService.getAllGroups();
        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();

        List<MarkStamp>markStamps=markStampService.findByAll();
        model.addAttribute("groups",studentsGroups);
        model.addAttribute("students",students);
        model.addAttribute("subjects",subjects);
        model.addAttribute("teachers",teachers);
        model.addAttribute("markTypes",MarkType.values());
        model.addAttribute("marks",Mark.values());
        model.addAttribute("markStamps",markStamps);
        model.addAttribute("selectGroup",0);
        model.addAttribute("type","progress");
        return new ModelAndView("admin/journal/journalList");
    }

    @RequestMapping(value = "/filter",params = {"group", "type"})
    public ModelAndView groupListFilter(@RequestParam(value = "group") int groupId,
                                        @RequestParam(value = "type") String type,
                                        Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        List<StudentsGroup> studentsGroups=groupService.getAllGroups();
        List<Jurnal> jurnals = new LinkedList<>();
        if (groupId==0){
            jurnals = jurnalService.getAllJurnals();
        }
        else{
            StudentsGroup studentsGroup = groupService.getGroupById(groupId);
            jurnals = jurnalService.findByGroup(studentsGroup);
        }


        List<MarkStamp>markStamps=new LinkedList<>();
        for (Jurnal j:
             jurnals) {
            for (MarkStamp st:
                 j.getMarkStamps()) {
                if(type.equals("progress") && st.getMarkType()!=MarkType.VISIT){
                    markStamps.add(st);
                }
                else if (type.equals("visit") && st.getMarkType()==MarkType.VISIT){
                    markStamps.add(st);
                }

            }
        }


        List<Student> students = studentService.getAllStudents();
        List<Subject> subjects = subjectService.getAllSubjects();
        List<Teacher> teachers = teacherService.getAllTeachers();


        model.addAttribute("groups",studentsGroups);
        model.addAttribute("students",students);
        model.addAttribute("subjects",subjects);
        model.addAttribute("teachers",teachers);
        model.addAttribute("markTypes",MarkType.values());
        model.addAttribute("marks",Mark.values());
        model.addAttribute("markStamps",markStamps);
        model.addAttribute("selectGroup",groupId);
        model.addAttribute("type",type);
        return new ModelAndView("admin/journal/journalList");
    }

    @RequestMapping("/journalList/delete")
    public ModelAndView delete(@RequestParam(value = "id") int id, Model model){
        MarkStamp markStamp = markStampService.findById(id);
        markStampService.delMarkStamp(markStamp);

        String message = markStamp.getDateString();
        message+=" "+markStamp.getJurnal().getStudent().getName();
        message+=" "+markStamp.getJurnal().getStudent().getSurname();
        message+=" "+markStamp.getJurnal().getSubject().getName();
        message+=" "+markStamp.getJurnal().getTeacher().getName()+" "+markStamp.getJurnal().getTeacher().getSurname();

        model.addAttribute("message", message);
        model.addAttribute("alert", 1);
        return groupList(model);
    }

    @RequestMapping("/journalList/edit")
    public ModelAndView editMark(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_student") int studentId,
                                    @RequestParam(value = "j_subject") int subjectId,
                                    @RequestParam(value = "j_group") int groupId,
                                    @RequestParam(value = "j_teacher") int teacherId,
                                    @RequestParam(value = "j_markType") String markType,
                                    @RequestParam(value = "j_mark") int mark,
                                    Model model){

        MarkStamp markStamp = markStampService.findById(id);

        Student student = studentService.getStudentById(studentId);
        Subject subject = subjectService.findById(subjectId);
        StudentsGroup studentsGroup = groupService.getGroupById(groupId);
        Teacher teacher = teacherService.findById(teacherId);
        MarkType markType1 = MarkType.markTypeFromString(markType);
        Jurnal jurnal = markStamp.getJurnal();

        jurnal.setStudent(student);
        jurnal.setSubject(subject);
        jurnal.setStudentsGroup(studentsGroup);
        jurnal.setTeacher(teacher);
        markStamp.setJurnal(jurnal);
        markStamp.setMark(Mark.MarkFromIntValue(mark));
        markStamp.setMarkType(markType1);
        markStampService.addOrUpdateMarkStamp(markStamp);

        String message = markStamp.getDateString();
        message+=" "+markStamp.getJurnal().getStudent().getName();
        message+=" "+markStamp.getJurnal().getStudent().getSurname();
        message+=" "+markStamp.getJurnal().getSubject().getName();
        message+=" "+markStamp.getJurnal().getTeacher().getName()+" "+markStamp.getJurnal().getTeacher().getSurname();

        model.addAttribute("message", message);
        model.addAttribute("alert", 2);

        return groupListFilter(studentsGroup.getId(), "progress", model);

    }
}
