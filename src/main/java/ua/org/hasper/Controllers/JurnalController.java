package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.org.hasper.Entity.Jurnal;
import ua.org.hasper.Entity.MarkStamp;
import ua.org.hasper.Entity.Subject;
import ua.org.hasper.service.JurnalService;
import ua.org.hasper.service.SubjectService;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class JurnalController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private JurnalService jurnalService;

    @RequestMapping("/journal")
    public ModelAndView journal(Model model) {
        Calendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_MONTH, -6);
        Date endDate = new Date();
        return list(null, startDate.getTime(), endDate, model);
    }

    public ModelAndView list(Subject subject, Date startDate, Date endDate, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Calendar sdt = new GregorianCalendar();
        sdt.setTime(startDate);
        Calendar edt = new GregorianCalendar();
        edt.setTime(endDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        List<String> headerCalendar = new LinkedList<>();
        List<Subject> subjects;
        List<Jurnal> jurnals;
        if (subject == null) {
            jurnals = jurnalService.findByLogin(user.getUsername(), sdt, edt);
            subjects = subjectService.getAllSubjects();
        } else {
            jurnals = jurnalService.findByLoginSubject(user.getUsername(), sdt, edt, subject);
            subjects = new LinkedList<>();
            subjects.add(subject);
            model.addAttribute("curSubject", subject);
        }


        Calendar dateCounter = new GregorianCalendar();
        dateCounter.setTime(startDate);

        while (dateCounter.before(edt)) {
            String tmp = dateFormat.format(dateCounter.getTime());
            headerCalendar.add(tmp);
            dateCounter.add(Calendar.DAY_OF_WEEK, 1);
        }
        model.addAttribute("headerCalendar", headerCalendar);

        String outtbl = "<tr>";


        for (Subject s : subjects) {
            sdt.setTime(startDate);
            outtbl += "<td>" + s.getName() + "</td>";
            while (sdt.before(edt)) {
                outtbl += "<td>";

                int c = 0;
                for (Jurnal jurnal : jurnals) {

                    for (MarkStamp markStamp : jurnal.getMarkStamps()) {
                        if (jurnal.getSubject().equals(s) &&
                                markStamp.getDate().get(Calendar.YEAR) == sdt.get(Calendar.YEAR) &&
                                markStamp.getDate().get(Calendar.MONTH) == sdt.get(Calendar.MONTH) &&
                                markStamp.getDate().get(Calendar.DAY_OF_MONTH) == sdt.get(Calendar.DAY_OF_MONTH)) {
                            if (c++ > 0) {
                                outtbl += " / ";
                            }
                            outtbl += markStamp.getMark().toString();
                        }
                    }
                }
                outtbl += "</td>";
                sdt.add(Calendar.DAY_OF_WEEK, 1);
            }

            outtbl += "</tr>";
        }

        model.addAttribute("jurtab", outtbl);
        subjects = subjectService.getAllSubjects();
        subjects.add(0, null);
        model.addAttribute("subjects", subjects);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        model.addAttribute("end_date", dateFormat.format(edt.getTime()));
        sdt.setTime(startDate);
        model.addAttribute("start_date", dateFormat.format(sdt.getTime()));
        model.addAttribute("login", user.getUsername());

        Collection<GrantedAuthority> roles = user.getAuthorities();
        for (GrantedAuthority ga :
                roles) {
            if (!ga.getAuthority().equals("ROLE_ADMIN") && !ga.getAuthority().equals("ROLE_TEACHER")) {
                model.addAttribute("noAdminHide", "class=\"hide\"");
            }
        }

        return new ModelAndView("journal");
    }

    @RequestMapping(value = "/journal", method = RequestMethod.POST)
    public ModelAndView filterJurnal(@RequestParam("j_subject") Integer subjectId,
                                     @RequestParam(value = "j_sdt") String sdt,
                                     @RequestParam(value = "j_edt") String edt,
                                     Model model) throws java.text.ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse(sdt);
        Date endDate = format.parse(edt);
        Subject subject = null;
        if (subjectId != null) {
            subject = subjectService.findById(subjectId);
        }

        return list(subject, startDate, endDate, model);
    }
}
