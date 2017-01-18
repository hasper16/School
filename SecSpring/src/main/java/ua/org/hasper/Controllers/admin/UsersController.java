package ua.org.hasper.Controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.UserRole;
import ua.org.hasper.service.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Pavel.Eremenko on 05.01.2017.
 */
@Controller
public class UsersController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private HomeWorkService homeWorkService;

    @RequestMapping("admin/users/add")
    public void add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        List<StudentsGroup> studentsGroups = new LinkedList<>();
        studentsGroups = groupService.getAllGroups();
        List<String> groupsName = new LinkedList<>();

        for (StudentsGroup sg : studentsGroups) {
            groupsName.add(sg.getName());
        }


        model.addAttribute("j_groups", groupsName);
        model.addAttribute("j_roles", UserRole.values());

        //return "admin/users/add";
    }

    @RequestMapping(value = "admin/users/add", method = RequestMethod.POST)
    public ModelAndView newUser(@RequestParam(value = "j_name") String name,
                                @RequestParam(value = "j_surname") String surname,
                                @RequestParam(value = "j_login") String login,
                                @RequestParam(value = "j_role") String role,
                                @RequestParam(value = "j_group") String group,
                                @RequestParam(value = "j_birthday") String birthday,
                                @RequestParam(value = "j_email") String email,
                                @RequestParam(value = "j_phone") long phone,
                                Model model) throws ParseException {
        String newPasswordStr = "password";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String newPassword = bCryptPasswordEncoder.encode(newPasswordStr);
        CustomUser customUser = new CustomUser(login, newPassword, UserRole.valueOf(role));
        userService.addUser(customUser);

        //parse Date
        Calendar cal;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(birthday);
        cal = Calendar.getInstance();
        cal.setTime(date);
        //end parse Date

        if (UserRole.valueOf(role).equals(UserRole.STUDENT)) {
            StudentsGroup studentsGroup = groupService.getGroupByName(group);
            Student student = new Student(name, surname, cal, phone, email, customUser, studentsGroup);
            studentService.addStudent(student);
        } else if (UserRole.valueOf(role).equals(UserRole.TEACHER)) {
            Teacher teacher = new Teacher(name, surname, cal, phone, email, customUser);
            teacherService.addOrUpdateTeacher(teacher);
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("addStatus", 1);

        return new ModelAndView("admin/users/add");
    }


    @RequestMapping("admin/users/list")
    public void list(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);

        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);

        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);

    }

    @RequestMapping("/student/delete")
    public ModelAndView deleteStudent(@RequestParam(value="id") int id, Model model) {

        Student student = studentService.getStudentById(id);
        String message = student.getName()+" "+student.getSurname();
        homeWorkStudentStatusService.deleteByStudent(student);

        List<Jurnal> jurnals = jurnalService.findByStudent(student);

        for (Jurnal j:jurnals) {
            jurnalService.delJurnal(j);
        }

        CustomUser customUser = userService.getUserById(student.getUser().getId());

        studentService.deleteStudent(student);
        userService.deleteUser(customUser);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",1);

        return new ModelAndView("admin/users/list");

    }

    @RequestMapping("/teacher/delete")
    public ModelAndView deleteTeacher(@RequestParam(value="id") int id, Model model) {

        Teacher teacher = teacherService.findById(id);
        String message = teacher.getName()+" "+teacher.getSurname();

        List<Jurnal> jurnals = jurnalService.findByTeacher(teacher);

        for (Jurnal j:jurnals) {
            j.setTeacher(null);
            jurnalService.addOrUpdateJurnal(j);
        }

        List<Schedule>schedules = scheduleService.findByTeacher(teacher);

        for (Schedule s:schedules) {
            s.setTeacher(null);
            scheduleService.addOrUpdateSchedule(s);
        }

        List<HomeWork>homeWorks = homeWorkService.findByTeacher(teacher);

        for (HomeWork h:
             homeWorks) {
            h.setTeacher(null);
            homeWorkService.addOrUpdateHomeWork(h);
        }

        CustomUser customUser = userService.getUserById(teacher.getUser().getId());

        teacherService.delTeacher(teacher);
        userService.deleteUser(customUser);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",1);

        return new ModelAndView("admin/users/list");

    }

    @RequestMapping("/admin/delete")
    public ModelAndView deleteAdmin(@RequestParam(value="id") int id, Model model) {

        CustomUser customUser = userService.getUserById(id);
        String message = customUser.getLogin();
        userService.deleteUser(customUser);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",1);

        return new ModelAndView("admin/users/list");

    }

    @RequestMapping("/teacher/edit")
    public ModelAndView editTeacher(@RequestParam(value="id") int id,
                                    @RequestParam(value = "j_name") String name,
                                    @RequestParam(value = "j_surname") String surname,
                                    @RequestParam(value = "j_login") String login,
                                    @RequestParam(value = "j_role") String role,
                                    @RequestParam(value = "j_birthday") String birthday,
                                    @RequestParam(value = "j_email") String email,
                                    @RequestParam(value = "j_phone") long phone,
                                    Model model) throws ParseException {
        Teacher teacher = teacherService.findById(id);

        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.getUser().setLogin(login);
        teacher.getUser().setRole(UserRole.valueOf(role));


        //parse Date
        Calendar cal;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(birthday);
        cal = Calendar.getInstance();
        cal.setTime(date);
        //end parse Date

        teacher.setBirthday(cal);
        teacher.setEmail(email);
        teacher.setPhone(phone);

        teacherService.addOrUpdateTeacher(teacher);

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);
        String message = teacher.getName()+" "+teacher.getSurname();
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",2);

        return new ModelAndView("admin/users/list");

    }

    @RequestMapping("/student/edit")
    public ModelAndView editStudent(@RequestParam(value="id") int id,
                                    @RequestParam(value = "j_name") String name,
                                    @RequestParam(value = "j_surname") String surname,
                                    @RequestParam(value = "j_login") String login,
                                    @RequestParam(value = "j_role") String role,
                                    @RequestParam(value = "j_birthday") String birthday,
                                    @RequestParam(value = "j_email") String email,
                                    @RequestParam(value = "j_phone") long phone,
                                    @RequestParam(value = "j_group") String group,
                                    Model model) throws ParseException {
        Student student = studentService.getStudentById(id);

        student.setName(name);
        student.setSurname(surname);
        student.getUser().setLogin(login);
        student.getUser().setRole(UserRole.valueOf(role));


        //parse Date
        Calendar cal;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(birthday);
        cal = Calendar.getInstance();
        cal.setTime(date);
        //end parse Date

        student.setBirthday(cal);
        student.setEmail(email);
        student.setPhone(phone);
        student.setStudentsGroup(groupService.getGroupByName(group));

        studentService.addStudent(student);

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        String message = student.getName()+" "+student.getSurname();
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",2);

        return new ModelAndView("admin/users/list");

    }

    @RequestMapping("/admin/edit")
    public ModelAndView editStudent(@RequestParam(value="id") int id,
                                    @RequestParam(value = "j_login") String login,
                                    @RequestParam(value = "j_role") String role,
                                    Model model) throws ParseException {
        CustomUser customUser = userService.getUserById(id);
        customUser.setLogin(login);
        customUser.setRole(UserRole.valueOf(role));

        userService.addUser(customUser);

        List<Student> students = studentService.getAllStudents();
        List<Teacher> teachers = teacherService.getAllTeachers();
        List<CustomUser> customUsers = userService.getUsersByRole(UserRole.ADMIN);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        List<StudentsGroup>studentsGroups=groupService.getAllGroups();
        model.addAttribute("allGroups",studentsGroups);
        String message = customUser.getLogin();
        model.addAttribute("students",students);
        model.addAttribute("teachers",teachers);
        model.addAttribute("admins",customUsers);
        model.addAttribute("message",message);
        model.addAttribute("alert",2);

        return new ModelAndView("admin/users/list");

    }
}