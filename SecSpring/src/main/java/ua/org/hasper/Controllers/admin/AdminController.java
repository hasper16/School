package ua.org.hasper.Controllers.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class AdminController {
    @RequestMapping("admin/")
    public String admini(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login",user.getUsername());


        return "admin/index";
    }
}
