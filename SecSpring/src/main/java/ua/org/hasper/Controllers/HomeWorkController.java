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
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWorkStudentStatus;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.service.HomeWorkStudentStatusService;
import ua.org.hasper.service.StudentService;
import ua.org.hasper.service.SubjectService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class HomeWorkController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;

    @RequestMapping(value = "/homework_filter", method = RequestMethod.POST)
    public ModelAndView seatchHomeWork(@RequestParam(value = "j_subject") String subject,
                                       @RequestParam(value = "j_status") String status,
                                       @RequestParam(value = "j_sdt") String sdt,
                                       @RequestParam(value = "j_edt") String edt,
                                       Model model) throws ParseException, java.text.ParseException {
        ;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add(subject);
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add(status);
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        model.addAttribute("hmstatus", status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar enddate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(dateFormat.parse(sdt));
        enddate.setTime(dateFormat.parse(edt));
        enddate.add(Calendar.HOUR_OF_DAY, 24);
        enddate.add(Calendar.SECOND, -1);

        Subject fSubject = subjectService.findByName(subject);

        HomeWorkStatus fStatus = null;
        for (HomeWorkStatus h : HomeWorkStatus.values()) {
            if (h.toString().equals(status)) {
                fStatus = h;
            }
        }

        model.addAttribute("end_date", edt);
        model.addAttribute("start_date", sdt);

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();

        Student student = studentService.getStudentByLogin(user.getUsername());

        if (fStatus != null && fSubject != null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentSubjectStatusDate(student, fSubject, fStatus, startDate, enddate);
        } else if (fStatus == null && fSubject != null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentSubjectDate(student, fSubject, startDate, enddate);
        } else if (fStatus != null && fSubject == null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentStatusDate(student, fStatus, startDate, enddate);
        } else {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(student, startDate, enddate);
        }


        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return new ModelAndView("homework");

    }

    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public ModelAndView updateStatusHomeWork(@RequestParam(value = "j_id") String id,
                                             @RequestParam(value = "j_status") String status,
                                             @RequestParam(value = "home_work_seach.j_sdt") String sdt,
                                             Model model) throws ParseException {

        HomeWorkStudentStatus homeWorkStudentStatus = homeWorkStudentStatusService.findById(Integer.parseInt(id));
        HomeWorkStatus newStatus = null;
        for (HomeWorkStatus h : HomeWorkStatus.values()) {
            if (h.toString().equals(status)) {
                newStatus = h;
            }
        }
        homeWorkStudentStatus.setStatus(newStatus);
        homeWorkStudentStatusService.saveOrUpdateHomeWorkStudentStatus(homeWorkStudentStatus);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        //model.addAttribute("subject",subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        //model.addAttribute("hmstatus" ,status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sysdate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        /*model.addAttribute("end_date",dateFormat.format(sysdate.getTime()));
        model.addAttribute("start_date",dateFormat.format(startDate.getTime()));*/

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();
        homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(studentService.getStudentByLogin(user.getUsername()), startDate, sysdate);

        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return new ModelAndView("homework");

    }

    @RequestMapping("/homework")
    public String homework(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        model.addAttribute("hmstatus", status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sysdate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        model.addAttribute("end_date", dateFormat.format(sysdate.getTime()));
        model.addAttribute("start_date", dateFormat.format(startDate.getTime()));

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();
        homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(studentService.getStudentByLogin(user.getUsername()), startDate, sysdate);

        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return "homework";
    }
}
