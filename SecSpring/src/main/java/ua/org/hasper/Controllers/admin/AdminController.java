package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Reports.MarkStatistics;
import ua.org.hasper.service.*;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class AdminController {

    @Autowired
    private ReportsService reportsService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private MarkStampService markStampService;

    @RequestMapping("/admin/")
    public ModelAndView index (Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login",user.getUsername());

        Calendar sdt = new GregorianCalendar();
        Calendar edt = new GregorianCalendar();
        sdt.add(Calendar.MONTH,-1);

        List<MarkStatistics>statisticss = reportsService.getMarkStatistic(sdt,edt);
        model.addAttribute("statistic",statisticss);
        Integer countStudents = studentService.countStudents();
        Integer countMarks = markStampService.countMarkStamps();
        Integer countHomeWorks = homeWorkService.countHomeWorks();
        Integer countTeachers = teacherService.countTeachers();
        model.addAttribute("countStudents",countStudents);
        model.addAttribute("countMarks",countMarks);
        model.addAttribute("countHomeWorks",countHomeWorks);
        model.addAttribute("countTeachers",countTeachers);

        return new ModelAndView("/admin/index");
    }
}
