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
import ua.org.hasper.Entity.HomeWork;
import ua.org.hasper.Entity.Jurnal;
import ua.org.hasper.Entity.Schedule;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.service.HomeWorkService;
import ua.org.hasper.service.JurnalService;
import ua.org.hasper.service.ScheduleService;
import ua.org.hasper.service.SubjectService;

import java.util.List;

/**
 * Created by Pavel.Eremenko on 11.01.2017.
 */
@Controller
public class SubjectsController {

    @Autowired
    SubjectService subjectService;
    @Autowired
    HomeWorkService homeWorkService;
    @Autowired
    JurnalService jurnalService;
    @Autowired
    ScheduleService scheduleService;

    @RequestMapping("admin/subjects/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/subjects/add");
    }

    @RequestMapping(value = "admin/subjects/add", method = RequestMethod.POST)
    public ModelAndView newSubject(@RequestParam(value = "j_subject") String subjectHtml,
                                 Model model)  {
        Subject subject = new Subject(subjectHtml);
        subjectService.addOrUpdateSubject(subject);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("addStatus", 1);

        return new ModelAndView("admin/subjects/add");
    }

    @RequestMapping("admin/subjects/list")
    public void list(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<Subject> subjects = subjectService.getAllSubjects();


        model.addAttribute("subjects",subjects);

    }

    @RequestMapping("/subject/delete")
    public ModelAndView deleteSubject(@RequestParam(value="id") int id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        String message ;
        Subject subject = subjectService.findById(id);
        List<HomeWork>homeWorks = homeWorkService.findBySubject(subject);
        List<Jurnal>jurnals=jurnalService.findBySubject(subject);
        List<Schedule>schedules=scheduleService.findBySubject(subject);


        for (HomeWork h:
                homeWorks) {
            h.setSubject(null);
            homeWorkService.addOrUpdateHomeWork(h);
        }

        for (Jurnal j:
                jurnals) {
            jurnalService.delJurnal(j);
        }

        for (Schedule s:
                schedules) {
            scheduleService.delSchedule(s);
        }




        message=subject.getName();
        subjectService.delSubject(subject);

        List<Subject> studentsGroups = subjectService.getAllSubjects();
        model.addAttribute("subjects",studentsGroups);


        model.addAttribute("message",message);
        model.addAttribute("alert",1);

        return new ModelAndView("admin/subjects/list");

    }


    @RequestMapping("/subject/edit")
    public ModelAndView editSubject(@RequestParam(value="id") int id,
                                    @RequestParam(value = "j_subject") String subjectName,
                                    Model model)  {
        Subject subject = subjectService.findById(id);
        subject.setName(subjectName);
        subjectService.addOrUpdateSubject(subject);


        String message ;
        message=subject.getName();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        model.addAttribute("message",message);
        model.addAttribute("alert",2);

        List<Subject> studentsGroups = subjectService.getAllSubjects();
        model.addAttribute("subjects",studentsGroups);

        return new ModelAndView("admin/subjects/list");

    }

}
