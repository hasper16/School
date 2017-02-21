package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.HomeWorkStudentStatus;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.service.HomeWorkStudentStatusService;
import ua.org.hasper.service.StudentService;
import ua.org.hasper.service.SubjectService;

import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Calendar.DAY_OF_MONTH;

@Controller
public class HomeWorkController {
    public static final int PAGESIZE = 25;
    int curPage = 0;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;
    private Subject subject;
    private Calendar startDate = new GregorianCalendar(2016, 1, 1);
    private Calendar endDate = new GregorianCalendar();
    private HomeWorkStatus homeWorkStatus;

    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public ModelAndView seatchHomeWork(@RequestParam(value = "j_subject") Integer subjectId,
                                       @RequestParam(value = "j_status") String status,
                                       @RequestParam(value = "j_sdt") String sdt,
                                       @RequestParam(value = "j_edt") String edt,
                                       Model model) throws ParseException, java.text.ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Student student = studentService.getStudentByLogin(user.getUsername());
        subject = subjectService.findById((subjectId == null) ? 0 : subjectId);
        homeWorkStatus = HomeWorkStatus.getStatusFromName(status);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        startDate.setTime(dateFormat.parse(sdt));
        endDate.setTime(dateFormat.parse(edt));

        return list(subject, startDate, endDate, homeWorkStatus, 0, model);
    }

    @RequestMapping(value = "/homework_", method = RequestMethod.POST)
    public ModelAndView updateStatusHomeWork(@RequestParam(value = "id") int id,
                                             @RequestParam(value = "j_hwStatus") String status,
                                             Model model) {
        HomeWorkStudentStatus homeWorkStudentStatus = homeWorkStudentStatusService.findById(id);
        homeWorkStudentStatus.setStatus(HomeWorkStatus.getStatusFromName(status));
        homeWorkStudentStatusService.saveOrUpdateHomeWorkStudentStatus(homeWorkStudentStatus);

        return list(subject, startDate, endDate, homeWorkStatus, curPage, model);

    }

    @RequestMapping("/homework")
    public ModelAndView homework(Model model) {
        startDate = new GregorianCalendar();
        endDate = new GregorianCalendar();
        startDate.add(DAY_OF_MONTH, -7);
        return list(null, startDate, endDate, null, 0, model);
    }

    @RequestMapping(value = "/homework_", params = {"page"})
    public ModelAndView homeworkPage(@RequestParam(value = "page") int page, Model model) {


        return list(subject, startDate, endDate, homeWorkStatus, page, model);
    }

    public ModelAndView list(Subject subject, Calendar startDate, Calendar endDate, HomeWorkStatus workStatus, int page, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        Student student = studentService.getStudentByLogin(user.getUsername());
        List<Subject> subjects;
        if (subject == null) {
            subjects = subjectService.getAllSubjects();
        } else {
            subjects = new LinkedList<>();
            subjects.add(subject);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sdt = dateFormat.format(startDate.getTime());
        String edt = dateFormat.format(endDate.getTime());

        List<HomeWorkStudentStatus> homeWorkStudentStatuses;
        Page<HomeWorkStudentStatus> homeWorkStudentStatusPage;
        curPage = page;
        int totalPages;
        if (workStatus == null) {
            if (subject == null) {
                homeWorkStudentStatusPage =
                        homeWorkStudentStatusService.findByStudentDate(student, startDate, endDate, page, PAGESIZE);

            } else {
                homeWorkStudentStatusPage = homeWorkStudentStatusService.findByStudentSubjectDate(student, subject, startDate, endDate, page, PAGESIZE);
            }
        } else {
            if (subject == null) {
                homeWorkStudentStatusPage = homeWorkStudentStatusService.findByStudentStatusDate(student, workStatus, startDate, endDate, page, PAGESIZE);
            } else {
                homeWorkStudentStatusPage = homeWorkStudentStatusService.findByStudentSubjectStatusDate(student, subject, workStatus, startDate, endDate, page, PAGESIZE);
            }

        }
        totalPages = homeWorkStudentStatusPage.getTotalPages() - 1;
        homeWorkStudentStatuses = homeWorkStudentStatusPage.getContent();

        model.addAttribute("homeworks", homeWorkStudentStatuses);
        model.addAttribute("subjects", subjects);
        model.addAttribute("statusList", HomeWorkStatus.values());
        model.addAttribute("hmstatus", HomeWorkStatus.values());
        model.addAttribute("curStatus", workStatus);
        model.addAttribute("subject", subject);
        model.addAttribute("start_date", sdt);
        model.addAttribute("end_date", edt);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }
        }

        return new ModelAndView("homework");
    }
}
