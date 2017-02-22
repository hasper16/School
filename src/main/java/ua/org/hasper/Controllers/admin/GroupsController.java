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
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.service.*;

import java.util.List;


@Controller
public class GroupsController {
    public static final int PAGESIZE = 25;
    List<StudentsGroup> studentsGroups;
    int curPage = 0;
    @Autowired
    private GroupService groupService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;

    @RequestMapping("admin/groups/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/groups/add");
    }

    @RequestMapping(value = "admin/groups/add", method = RequestMethod.POST)
    public ModelAndView newGroup(@RequestParam(value = "j_group") String group,
                                 Model model) {
        String message;
        int alert;
        try {
            List<StudentsGroup> studentsGroupDublicat = groupService.getGroupByName(group);
            if (studentsGroupDublicat.size() > 0) {
                alert = 2;
                message = "Группа с таким названием уже существует";
            } else {
                StudentsGroup studentsGroup = new StudentsGroup(group);
                groupService.addGroup(studentsGroup);
                alert = 1;
                message = "Группа " + studentsGroup.getName() + " успешно добавленна";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/groups/add");
    }

    @RequestMapping("admin/groups/list")
    public ModelAndView list(Model model) {
        return listFilter(0, model);
    }

    @RequestMapping(value = "admin/groups/list", params = {"page"})
    public ModelAndView listFilter(@RequestParam(value = "page") int page,
                                   Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<StudentsGroup> groupPage = groupService.getAllGroups(page, PAGESIZE);
        studentsGroups = groupPage.getContent();
        curPage = page;
        int totalPages = groupPage.getTotalPages();

        model.addAttribute("groups", studentsGroups);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return new ModelAndView("admin/groups/list");
    }

    @RequestMapping("/groups/delete")
    public ModelAndView deleteGroup(@RequestParam(value = "id") int id, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        StudentsGroup studentsGroup = groupService.getGroupById(id);
        int alert;
        String message;
        try {

            groupService.delGroup(studentsGroup);

            alert = 1;
            message = "Группа " + studentsGroup.getName() + " успешно удалена";
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("alert", alert);
        model.addAttribute("message", message);


        return listFilter(curPage, model);
    }


    @RequestMapping("/groups/edit")
    public ModelAndView editGroup(@RequestParam(value = "id") int id,
                                  @RequestParam(value = "j_group") String group,
                                  Model model) {
        String message = "";
        int alert = 0;
        try {
            StudentsGroup studentsGroup = groupService.getGroupById(id);
            List<StudentsGroup> studentsGroupDublicat = groupService.getGroupByName(group);
            if (studentsGroupDublicat.size() > 0) {
                for (StudentsGroup s :
                        studentsGroupDublicat) {
                    if (s.getId() != studentsGroup.getId()) {
                        alert = 2;
                        message = "Группа с таким названием уже существует";
                    }
                }
            }
            if (alert != 2) {
                studentsGroup.setName(group);
                groupService.editGroup(studentsGroup);
                alert = 1;
                message = "Группа " + studentsGroup.getName() + "успешно измененна";
            }
        } catch (Exception e) {
            message = "Ошибка (" + e.getMessage() + ")";
            alert = 3;
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return listFilter(curPage, model);

    }

}
