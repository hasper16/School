package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.*;
import ua.org.hasper.Entity.Enums.HomeWorkStatus;
import ua.org.hasper.Entity.Enums.WeekDayName;
import ua.org.hasper.service.*;

import java.text.*;
import java.util.*;

@Controller
public class MyController {

    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("login", user.getUsername());
        model.addAttribute("roles", user.getAuthorities());


        return "index";
    }


    @RequestMapping("/admin")
    public String admin(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return "admin";
    }




}