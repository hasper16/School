package ua.org.hasper.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by Pavel.Eremenko on 04.01.2017.
 */
@Controller
public class JurnalController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private JurnalService jurnalService;

    @RequestMapping("/journal")
    public String journal(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = new GregorianCalendar();
        List<String> headerCalendar = new LinkedList<>();
        calendar.add(Calendar.DAY_OF_WEEK, -6);

        for (int i = 1; i <= 7; i++) {
            String tmp = dateFormat.format(calendar.getTime());
            headerCalendar.add(tmp);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
        }
        model.addAttribute("headerCalendar", headerCalendar);

        Calendar sdt = new GregorianCalendar();
        sdt.add(Calendar.DAY_OF_WEEK, -6);

        Calendar edt = new GregorianCalendar();
        /*System.out.println(dateFormat.format(edt));
        edt.set(Calendar.HOUR_OF_DAY,23);
        edt.set(Calendar.MINUTE, 59);
        edt.set(Calendar.SECOND, 59);
        edt.set(Calendar.MILLISECOND, 999);
        System.out.println(dateFormat.format(edt));*/
        Map<Subject, Jurnal> j = new TreeMap<>();
        String strsdt = dateFormat.format(sdt.getTime());
        String stredt = dateFormat.format(edt.getTime());
        j = jurnalService.findByLoginForMap(user.getUsername(), sdt, edt);

        //---------------------------------------------
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        for (Map.Entry entry :
                j.entrySet()) {
            Subject subject = (Subject) entry.getKey();
            System.out.println(subject.getName());
            Jurnal jurnal = (Jurnal) entry.getValue();
            for (MarkStamp markStamp :
                    jurnal.getMarkStamps()) {
                String sDate = simpleDateFormat.format(markStamp.getDate().getTime());
                String sMark = markStamp.getMark().toString();
                System.out.println(sDate + " - " + sMark);
            }

        }

        List<Jurnal> jurnals = jurnalService.findByLogin(user.getUsername(), sdt, edt);
        List<Subject> subjectsT = subjectService.getAllSubjects();
        Collections.sort(subjectsT);
        String outtbl = "<tr>";
        for (Subject subject : subjectsT) {
            outtbl += "<td>" + subject.getName() + "</td>";
            int st = sdt.getTime().getDate();
            int en = edt.getTime().getDate();
            while (st <= en) {
                outtbl += "<td>";
                for (Jurnal jurnal : jurnals) {
                    for (MarkStamp markStamp : jurnal.getMarkStamps()) {
                        if (jurnal.getSubject().equals(subject) && markStamp.getDate().getTime().getDate() == st) {
                            outtbl += markStamp.getMark().toString();
                        }
                    }
                }
                outtbl += "</td>";
                st++;
            }
            outtbl += "</tr>";
        }


        //-----------------------------------------------
        /*Calendar cal = new GregorianCalendar();*/




       /* model.addAttribute("jurnal", j);
*/
  /*      String outtbl = "<tr>";
        for (Map.Entry entry:j.entrySet()) {
            Subject s= (Subject) entry.getKey();
            Jurnal jj= (Jurnal) entry.getValue();
            outtbl+="<td>"+s.getName()+"</td>";
            System.out.print(outtbl);
            int st = sdt.getTime().getDate();
            int en = edt.getTime().getDate();
            while (st!=en) {
                for (MarkStamp markStamp : jj.getMarkStamps()) {
                    //Calendar c = (Calendar) entry1.getKey();
                    outtbl+="<td>";
                    if (markStamp.getDate().getTime().getDate() == st) {
                        outtbl += markStamp.getMark().toString();
                    }
                    outtbl+="</td>";
                    st++;

                }
            }
            outtbl += "</tr>";
        }*/
        model.addAttribute("jurtab", outtbl);


        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("end_date", dateFormat1.format(edt.getTime()));
        model.addAttribute("start_date", dateFormat1.format(sdt.getTime()));
        return "journal";
    }

    @RequestMapping(value = "/journal_filter", method = RequestMethod.POST)
    public ModelAndView filterJurnal(@RequestParam("j_subject") String sub,
                                     @RequestParam(value = "j_sdt") String sdt,
                                     @RequestParam(value = "j_edt") String edt,
                                     Model model) throws java.text.ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar enddate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(dateFormat.parse(sdt));
        enddate.setTime(dateFormat.parse(edt));
        enddate.add(Calendar.HOUR_OF_DAY, 24);
        enddate.add(Calendar.SECOND, -1);

        Subject subject = subjectService.findByName(sub);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateFormat.parse(sdt));
        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<String> headerCalendar = new LinkedList<>();
        int st = startDate.getTime().getDate();
        int en = enddate.getTime().getDate();


        while (st <= en) {
            String tmp = dateFormat.format(calendar.getTime());
            headerCalendar.add(tmp);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            st++;
        }
        model.addAttribute("headerCalendar", headerCalendar);

        List<Jurnal> jurnals = jurnalService.findByLoginSubject(user.getUsername(), startDate, enddate, subject);

        String outtbl = "<tr>";

        outtbl += "<td>" + subject.getName() + "</td>";
        st = startDate.getTime().getDate();
        en = enddate.getTime().getDate();
        while (st <= en) {
            outtbl += "<td>";
            for (Jurnal jurnal : jurnals) {
                for (MarkStamp markStamp : jurnal.getMarkStamps()) {
                    if (jurnal.getSubject().equals(subject) && markStamp.getDate().getTime().getDate() == st) {
                        outtbl += markStamp.getMark().toString();
                    }
                }
            }
            outtbl += "</td>";
            st++;
        }
        outtbl += "</tr>";

        model.addAttribute("jurtab", outtbl);


        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add(sub);
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("end_date", dateFormat1.format(enddate.getTime()));
        model.addAttribute("start_date", dateFormat1.format(startDate.getTime()));


        return new ModelAndView("journal");
    }
}
