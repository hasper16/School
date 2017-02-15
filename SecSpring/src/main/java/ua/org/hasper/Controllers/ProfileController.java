package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.CustomUser;
import ua.org.hasper.Entity.Photo;
import ua.org.hasper.Entity.Student;
import ua.org.hasper.service.PhotoService;
import ua.org.hasper.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class ProfileController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping("/profile")
    public String profile(Model model) {// throws UsernameNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        String login = user.getUsername();
        Student student = studentService.getStudentByLogin(login);
        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_STUDENT")) {
                String d = "disabled=\"disabled\"";
                model.addAttribute("buttonStatus", d);

            } else if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }
        }

        model.addAttribute("student", student);

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateProfile(@RequestParam(value = "j_login") String login,
                                      @RequestParam(value = "j_password") String password,
                                      @RequestParam(value = "j_name") String name,
                                      @RequestParam(value = "j_surname") String surname,
                                      @RequestParam(value = "j_birthday") String birthday,
                                      @RequestParam(value = "j_email") String email,
                                      @RequestParam(value = "j_phone") long phone,
                                      @RequestParam(value = "j_photo") MultipartFile photo,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      Model model) throws ParseException, java.text.ParseException {
        int alert = 0;
        String message = "";
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) auth.getPrincipal();
            Student student = studentService.getStudentByLogin(user.getUsername());
            CustomUser customUser = student.getUser();
            customUser.setLogin(login);
            if (!password.equals("")) {
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                String newPassword = bCryptPasswordEncoder.encode(password);
                customUser.setPassword(newPassword);
            }
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
            try {
                if (!photo.isEmpty()) {
                    Photo photoF = new Photo(photo.getOriginalFilename(), photo.getBytes());
                    photoService.add(photoF);
                    student.setPhoto(photoF);
                }
            } catch (IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
            studentService.addStudent(student);

            User updatedUser =  (User)userDetailsService.loadUserByUsername(student.getUser().getLogin());

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(updatedUser,
                            null,
                            user.getAuthorities());

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authentication);

            alert = 1;
            message = "Изменения в профиль успешно внесены";

            model.addAttribute("login", updatedUser.getUsername());
            model.addAttribute("name", student.getName());
            model.addAttribute("surname", student.getSurname());
            model.addAttribute("birthday", student.getBirthday());
            model.addAttribute("email", student.getEmail());
            model.addAttribute("phone", student.getPhone());
            model.addAttribute("student", student);
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }
        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return new ModelAndView("profile");

    }

    @RequestMapping("/image/{file_id}")
    public void getFile(HttpServletRequest request, HttpServletResponse response, @PathVariable("file_id") long fileId) {
        try {
            byte[] content = photoService.findById(fileId).getBody();
            response.setContentType("image/png");
            response.getOutputStream().write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}