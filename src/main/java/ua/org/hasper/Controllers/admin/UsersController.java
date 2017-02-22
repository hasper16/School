package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Controllers.Tools;
import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.Entity.StudentsGroup;
import ua.org.hasper.Entity.Teacher;
import ua.org.hasper.service.*;

import java.text.ParseException;
import java.util.List;

@Controller
public class UsersController {
    public static final int PAGESIZE = 25;
    int curPage = 0;
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping("admin/users/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<StudentsGroup> studentsGroups = groupService.getAllGroups();

        model.addAttribute("j_groups", studentsGroups);
        model.addAttribute("j_roles", UserRole.values());

        return new ModelAndView("admin/users/add");
    }

    @RequestMapping(value = "admin/users/add", method = RequestMethod.POST)
    public ModelAndView newUser(@RequestParam(defaultValue = "", value = "j_name", required = false) String name,
                                @RequestParam(defaultValue = "", value = "j_surname", required = false) String surname,
                                @RequestParam(value = "j_login") String login,
                                @RequestParam(value = "j_role") String role,
                                @RequestParam(defaultValue = "-1", value = "j_group", required = false) Integer groupId,
                                @RequestParam(defaultValue = "", value = "j_birthday", required = false) String birthday,
                                @RequestParam(defaultValue = "", value = "j_email", required = false) String email,
                                @RequestParam(defaultValue = "", value = "j_phone", required = false) Long phone,
                                Model model) throws ParseException {
        String newPasswordStr = "password";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newPassword = bCryptPasswordEncoder.encode(newPasswordStr);


        int alert;
        String message;

        UserRole userRole = UserRole.RoleByName(role);
        CustomUser customUser = new CustomUser(login, newPassword, userRole);
        try {
            if (userService.getUserByLogin(login) != null) {
                alert = 2;
                message = "Пользователь с таким логином уже существует";
                model.addAttribute("j_name", name);
                model.addAttribute("j_surname", surname);
                model.addAttribute("j_login", login);
                model.addAttribute("j_birthday", birthday);
                model.addAttribute("j_email", email);
                model.addAttribute("j_phone", phone);
            } else if (userRole.equals(UserRole.ROLE_ADMIN)) {
                userService.addUser(customUser);
                alert = 1;
                message = "Пользователь c ролью " + UserRole.ROLE_ADMIN.toString() + " логин " + customUser.getLogin() + " успешно добавлен";
            } else if (userRole.equals(UserRole.ROLE_STUDENT)) {
                StudentsGroup studentsGroup = groupService.getGroupById(groupId);
                Student student = new Student(name, surname, Tools.parteHTMLDate(birthday), phone, email, customUser, studentsGroup, photoService.findById(1));
                userService.addUser(customUser);
                studentService.addStudent(student);
                alert = 1;
                message = "Пользователь c ролью " + UserRole.ROLE_STUDENT.toString() + " логин " + customUser.getLogin() + " успешно добавлен";
            } else if (userRole.equals(UserRole.ROLE_TEACHER)) {
                Teacher teacher = new Teacher(name, surname, Tools.parteHTMLDate(birthday), phone, email, customUser);
                userService.addUser(customUser);
                teacherService.addOrUpdateTeacher(teacher);
                alert = 1;
                message = "Пользователь c ролью " + UserRole.ROLE_TEACHER.toString() + " логин " + customUser.getLogin() + " успешно добавлен";
            } else {
                alert = 2;
                message = "Не все поля заполнены";
                model.addAttribute("j_name", name);
                model.addAttribute("j_surname", surname);
                model.addAttribute("j_login", login);
                model.addAttribute("j_birthday", birthday);
                model.addAttribute("j_email", email);
                model.addAttribute("j_phone", phone);
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("alert", alert);
        model.addAttribute("message", message);

        return add(model);
    }


    @RequestMapping("admin/users/teachers")
    public ModelAndView teachersList(Model model) {

        return teachersListPage(0, model);
    }

    @RequestMapping("admin/users/students")
    public ModelAndView studentsList(Model model) {
        return studentsListPage(0, model);
    }

    @RequestMapping("admin/users/admins")
    public ModelAndView adminsList(Model model) {
        return adminsListPage(0, model);
    }


    @RequestMapping(value = "admin/users/teachers", params = {"page"})
    public ModelAndView teachersListPage(@RequestParam(value = "page") int page,
                                         Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<Teacher> teacherPage = teacherService.getAllTeachers(page, PAGESIZE);
        curPage = page;
        int totalPages = teacherPage.getTotalPages() - 1;
        List<Teacher> teachers = teacherPage.getContent();

        model.addAttribute("teachers", teachers);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);

        return new ModelAndView("admin/users/teachers");
    }

    @RequestMapping(value = "admin/users/students", params = {"page"})
    public ModelAndView studentsListPage(@RequestParam(value = "page") int page,
                                         Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<Student> studentPage = studentService.getAllStudents(page, PAGESIZE);
        curPage = page;
        int totalPages = studentPage.getTotalPages();
        List<Student> students = studentPage.getContent();
        List<StudentsGroup> studentsGroups = groupService.getAllGroups();

        model.addAttribute("allGroups", studentsGroups);
        model.addAttribute("students", students);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        return new ModelAndView("admin/users/students");
    }

    @RequestMapping(value = "admin/users/admins", params = {"page"})
    public ModelAndView adminsListPage(@RequestParam(value = "page") int page,
                                       Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Page<CustomUser> userPage = userService.getUsersByRole(UserRole.ROLE_ADMIN, page, PAGESIZE);
        curPage = page;
        int totalPages = userPage.getTotalPages();
        List<CustomUser> customUsers = userPage.getContent();

        model.addAttribute("admins", customUsers);
        model.addAttribute("curPage", curPage);
        model.addAttribute("totalPages", totalPages);
        return new ModelAndView("admin/users/admins");
    }


    @RequestMapping("/student/delete")
    public ModelAndView deleteStudent(@RequestParam(value = "id") int id, Model model) {

        Student student = studentService.getStudentById(id);
        int alert;
        String message;
        try {
            userService.deleteUser(student.getUser());
            message = "Пользователь " + student.getUser().getLogin() + "успешно удален";
            alert = 1;
        } catch (Exception e) {
            message = "Ошибка (" + e.getMessage() + ")";
            alert = 3;
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return studentsListPage(curPage, model);

    }

    @RequestMapping("/teacher/delete")
    public ModelAndView deleteTeacher(@RequestParam(value = "id") int id, Model model) {

        Teacher teacher = teacherService.findById(id);
        String message;
        int alert;

        try {
            userService.deleteUser(teacher.getUser());
            message = "Пользователь " + teacher.getUser().getLogin() + "успешно удален";
            alert = 1;
        } catch (Exception e) {
            message = "Ошибка (" + e.getMessage() + ")";
            alert = 3;
        }


        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return teachersListPage(curPage, model);

    }

    @RequestMapping("/admin/delete")
    public ModelAndView deleteAdmin(@RequestParam(value = "id") int id, Model model) {

        CustomUser customUser = userService.getUserById(id);
        String message;
        int alert;
        try {
            userService.deleteUser(customUser);
            message = "Пользователь " + customUser.getLogin() + "успешно удален";
            alert = 1;
        } catch (Exception e) {
            message = "Ошибка (" + e.getMessage() + ")";
            alert = 3;
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return adminsListPage(curPage, model);
    }

    @RequestMapping("/teacher/edit")
    public ModelAndView editTeacher(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_name") String name,
                                    @RequestParam(value = "j_surname") String surname,
                                    @RequestParam(value = "j_login") String login,
                                    @RequestParam(value = "j_birthday") String birthday,
                                    @RequestParam(value = "j_email") String email,
                                    @RequestParam(value = "j_phone") long phone,
                                    Model model) throws ParseException {
        Teacher teacher = teacherService.findById(id);

        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setBirthday(Tools.parteHTMLDate(birthday));
        teacher.setEmail(email);
        teacher.setPhone(phone);

        int alert;
        String message;
        CustomUser dublicateUser = userService.getUserByLogin(login);
        if (dublicateUser != null && dublicateUser.getId() != teacher.getUser().getId()) {
            alert = 2;
            message = "Пользователь с таким логином уже существует";
        } else {
            teacher.getUser().setLogin(login);
            teacherService.addOrUpdateTeacher(teacher);
            message = "Пользователь " + teacher.getUser().getLogin() + "успешно изменен";
            alert = 1;
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return teachersListPage(curPage, model);

    }

    @RequestMapping("/student/edit")
    public ModelAndView editStudent(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_name") String name,
                                    @RequestParam(value = "j_surname") String surname,
                                    @RequestParam(value = "j_login") String login,
                                    @RequestParam(value = "j_birthday") String birthday,
                                    @RequestParam(value = "j_email") String email,
                                    @RequestParam(value = "j_phone") long phone,
                                    @RequestParam(value = "j_group") int groupId,
                                    Model model) throws ParseException {
        Student student = studentService.getStudentById(id);

        student.setName(name);
        student.setSurname(surname);

        student.setBirthday(Tools.parteHTMLDate(birthday));
        student.setEmail(email);
        student.setPhone(phone);
        student.setStudentsGroup(groupService.getGroupById(groupId));
        String message;
        int alert;

        CustomUser dublicateUser = userService.getUserByLogin(login);
        if (dublicateUser != null && dublicateUser.getId() != student.getUser().getId()) {
            alert = 2;
            message = "Пользователь с таким логином уже существует";
        } else {
            student.getUser().setLogin(login);
            studentService.addStudent(student);
            message = "Пользователь " + student.getUser().getLogin() + "успешно изменен";
            alert = 1;
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return studentsListPage(curPage, model);

    }

    @RequestMapping("/admin/edit")
    public ModelAndView editStudent(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_login") String login,
                                    Model model) throws ParseException {
        CustomUser customUser = userService.getUserById(id);
        String message;
        int alert;

        CustomUser dublicateUser = userService.getUserByLogin(login);
        if (dublicateUser != null && dublicateUser.getId() != customUser.getId()) {
            alert = 2;
            message = "Пользователь с таким логином уже существует";
        } else {
            customUser.setLogin(login);
            userService.addUser(customUser);
            message = "Пользователь " + customUser.getLogin() + "успешно изменен";
            alert = 1;
        }

        model.addAttribute("message", message);
        model.addAttribute("alert", alert);

        return adminsListPage(curPage, model);

    }
}