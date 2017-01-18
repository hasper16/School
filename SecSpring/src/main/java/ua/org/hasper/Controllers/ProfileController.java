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
import ua.org.hasper.Entity.Student;
import ua.org.hasper.service.StudentService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class ProfileController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/profile")
    public String profile(Model model) {// throws UsernameNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        String login = user.getUsername();
        Student student = studentService.getStudentByLogin(login);
        model.addAttribute("name", student.getName());
        model.addAttribute("surname", student.getSurname());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        model.addAttribute("birthday", student.getBirthday());
        model.addAttribute("email", student.getEmail());
        model.addAttribute("phone", student.getPhone());

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateProfile(@RequestParam(value = "j_name") String name,
                                      @RequestParam(value = "j_surname") String surname,
                                      @RequestParam(value = "j_birthday") String birthday,
                                      @RequestParam(value = "j_email") String email,
                                      @RequestParam(value = "j_phone") long phone,
                                      Model model) throws ParseException, java.text.ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Student student = studentService.getStudentByLogin(user.getUsername());
        student.setName(name);
        student.setSurname(surname);
        Calendar cal;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(birthday);
        cal = Calendar.getInstance();
        cal.setTime(date);

        student.setBirthday(cal);
        student.setEmail(email);
        student.setPhone(phone);
        studentService.addStudent(student);


        model.addAttribute("login", user.getUsername());

        String login = user.getUsername();
        model.addAttribute("name", student.getName());
        model.addAttribute("surname", student.getSurname());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        model.addAttribute("birthday", student.getBirthday());
        model.addAttribute("email", student.getEmail());
        model.addAttribute("phone", student.getPhone());
        return new ModelAndView("profile");

    }
}
