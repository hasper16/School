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
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.service.HomeWorkService;
import ua.org.hasper.service.JurnalService;
import ua.org.hasper.service.ScheduleService;
import ua.org.hasper.service.SubjectService;

import java.util.List;

@Controller
public class SubjectsController {

    public static final int PAGESIZE = 25;
    @Autowired
    SubjectService subjectService;
    @Autowired
    HomeWorkService homeWorkService;
    @Autowired
    JurnalService jurnalService;
    @Autowired
    ScheduleService scheduleService;
    int curPage = 0;

    @RequestMapping("admin/subjects/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/subjects/add");
    }

    @RequestMapping(value = "admin/subjects/add", method = RequestMethod.POST)
    public ModelAndView newSubject(@RequestParam(value = "j_subject") String subjectHtml,
                                   Model model) {
        int alert;
        String message;
        try {
            if (subjectService.findByName(subjectHtml) != null) {
                alert = 2;
                message = "Предмет с таким названием уже существует";
                model.addAttribute("j_subject", subjectHtml);
            } else {
                Subject subject = new Subject(subjectHtml);
                subjectService.addOrUpdateSubject(subject);
                alert = 1;
                message = "Предмет " + subject.getName() + " успешно добавлен";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return new ModelAndView("admin/subjects/add");
    }

    @RequestMapping("admin/subjects/list")
    public ModelAndView list(Model model) {
        return listFilter(0, model);
    }

    @RequestMapping(value = "admin/subjects/list", params = {"page"})
    public ModelAndView listFilter(@RequestParam(value = "page") int page, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<Subject> subjectPage = subjectService.getAllSubjects(page, PAGESIZE);
        List<Subject> subjects = subjectPage.getContent();
        curPage = page;
        int totalPages = subjectPage.getTotalPages() - 1;

        model.addAttribute("subjects", subjects);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return new ModelAndView("admin/subjects/list");
    }

    @RequestMapping("/subject/delete")
    public ModelAndView deleteSubject(@RequestParam(value = "id") int id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        String message;
        int alert;
        Subject subject = subjectService.findById(id);

        try {
            subjectService.delSubject(subject);
            message = "Предмент " + subject.getName() + " успешно удален";
            alert = 1;
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return listFilter(curPage, model);
    }


    @RequestMapping("/subject/edit")
    public ModelAndView editSubject(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_subject") String subjectName,
                                    Model model) {
        int alert;
        String message;

        try {
            List<Subject> dublicat = subjectService.findByName(subjectName);
            Subject subject = subjectService.findById(id);
            if (dublicat.size() >= 1 && !dublicat.contains(subject)) {
                alert = 2;
                message = "Предмет с таким названием уже существует";
            } else {
                subject.setName(subjectName);
                subjectService.addOrUpdateSubject(subject);
                alert = 1;
                message = "Предмет " + subject.getName() + " успешно изменен";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return list(model);

    }

}
