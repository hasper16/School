package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.service.GroupService;
import ua.org.hasper.service.HomeWorkService;
import ua.org.hasper.service.MarkStampService;
import ua.org.hasper.service.StudentService;

import java.util.Collection;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MarkStampService markStampService;
    @Autowired
    private HomeWorkService homeWorkService;


    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }

            List<Student> top5Students = studentService.top5Students();
            List<StudentsGroup> top5Groups = groupService.top5Groups();
            model.addAttribute("top5Students", top5Students);
            model.addAttribute("top5Groups", top5Groups);
            Integer countStudents = studentService.countStudents();
            Integer countGroups = groupService.countGroups();
            Integer countMarks = markStampService.countMarkStamps();
            Integer countHomeWorks = homeWorkService.countHomeWorks();
            model.addAttribute("countStudents", countStudents);
            model.addAttribute("countGroups", countGroups);
            model.addAttribute("countMarks", countMarks);
            model.addAttribute("countHomeWorks", countHomeWorks);
        }
        return "index";
    }
}