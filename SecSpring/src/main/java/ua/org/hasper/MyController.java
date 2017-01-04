package ua.org.hasper;

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
public class MyController {//implements UserDetailsService {

    @Autowired
    private StudentService studentService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private JurnalService jurnalService;
    @Autowired
    private HomeWorkService homeWorkService;
    @Autowired
    private ScheduleTimesService scheduleTimesService;
    @Autowired
    private HomeWorkStudentStatusService homeWorkStudentStatusService;


    @RequestMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        model.addAttribute("login", user.getUsername());
        model.addAttribute("roles", user.getAuthorities());


        return "index";
    }

    @RequestMapping("/profile")
    public String profile(Model model) {// throws UsernameNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        String login = user.getUsername();
        Student student = studentService.getStudentByLogin(login);
        model.addAttribute("name", student.getName());
        model.addAttribute("surname", student.getSurname());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        model.addAttribute("birthday", dateFormat.format(student.getBirthday().getTime()));
        model.addAttribute("email", student.getEmail());
        model.addAttribute("phone", student.getPhone());

        return "profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView updateProfile(@RequestParam(value = "j_name") String name,
                                      @RequestParam(value = "j_surname") String surname,
                                      @RequestParam(value = "j_birthday") String birthday,
                                      @RequestParam(value = "j_email") String email,
                                      @RequestParam(value = "j_phone") int phone,
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


        model.addAttribute("birthday", dateFormat.format(student.getBirthday().getTime()));
        model.addAttribute("email", student.getEmail());
        model.addAttribute("phone", student.getPhone());
        return new ModelAndView("profile");

    }

    @RequestMapping("/schedule")
    public String schedule(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());


        StudentsGroup curStudentGroup = studentService.getStudentByLogin(user.getUsername()).getStudentsGroup();
        model.addAttribute("curStudentGroup", curStudentGroup.getName());
        List<StudentsGroup> studentsGroup = groupService.allGroupsWithout(curStudentGroup.getName());
        model.addAttribute("groupsname", studentsGroup);

        List<Schedule> monSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Monday, curStudentGroup);
        List<Schedule> tueSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Tuesday, curStudentGroup);
        List<Schedule> wedSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Wednesday, curStudentGroup);
        List<Schedule> thuSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Thursday, curStudentGroup);
        List<Schedule> friSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Friday, curStudentGroup);
        List<Schedule> satSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Saturday, curStudentGroup);

        model.addAttribute("monday", monSchedule);
        model.addAttribute("thuesday", tueSchedule);
        model.addAttribute("wednesday", wedSchedule);
        model.addAttribute("thurday", thuSchedule);
        model.addAttribute("friday", friSchedule);
        model.addAttribute("saturday", satSchedule);

        return "schedule";
    }

    @RequestMapping(value = "/schedule", method = RequestMethod.POST)
    public ModelAndView selectSchedule(@RequestParam(value = "class") String groupname,
                                       Model model) throws ParseException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        model.addAttribute("curStudentGroup", groupService.getGroupByName(groupname).getName());
        List<StudentsGroup> studentsGroup = groupService.allGroupsWithout(groupname);
        model.addAttribute("groupsname", studentsGroup);

        StudentsGroup curStudentGroup = groupService.getGroupByName(groupname);

        List<Schedule> monSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Monday, curStudentGroup);
        List<Schedule> tueSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Tuesday, curStudentGroup);
        List<Schedule> wedSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Wednesday, curStudentGroup);
        List<Schedule> thuSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Thursday, curStudentGroup);
        List<Schedule> friSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Friday, curStudentGroup);
        List<Schedule> satSchedule = scheduleService.findByWeekDayNameNGroup(WeekDayName.Saturday, curStudentGroup);

        model.addAttribute("monday", monSchedule);
        model.addAttribute("thuesday", tueSchedule);
        model.addAttribute("wednesday", wedSchedule);
        model.addAttribute("thurday", thuSchedule);
        model.addAttribute("friday", friSchedule);
        model.addAttribute("saturday", satSchedule);

        return new ModelAndView("schedule");

    }

    @RequestMapping(value = "/homework_filter", method = RequestMethod.POST)
    public ModelAndView seatchHomeWork(@RequestParam(value = "j_subject") String subject,
                                       @RequestParam(value = "j_status") String status,
                                       @RequestParam(value = "j_sdt") String sdt,
                                       @RequestParam(value = "j_edt") String edt,
                                       Model model) throws ParseException, java.text.ParseException {
        ;
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add(subject);
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add(status);
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        model.addAttribute("hmstatus", status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar enddate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.setTime(dateFormat.parse(sdt));
        enddate.setTime(dateFormat.parse(edt));
        enddate.add(Calendar.HOUR_OF_DAY, 24);
        enddate.add(Calendar.SECOND, -1);

        Subject fSubject = subjectService.findByName(subject);

        HomeWorkStatus fStatus = null;
        for (HomeWorkStatus h : HomeWorkStatus.values()) {
            if (h.toString().equals(status)) {
                fStatus = h;
            }
        }

        model.addAttribute("end_date", edt);
        model.addAttribute("start_date", sdt);

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();

        Student student = studentService.getStudentByLogin(user.getUsername());

        if (fStatus != null && fSubject != null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentSubjectStatusDate(student, fSubject, fStatus, startDate, enddate);
        } else if (fStatus == null && fSubject != null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentSubjectDate(student, fSubject, startDate, enddate);
        } else if (fStatus != null && fSubject == null) {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentStatusDate(student, fStatus, startDate, enddate);
        } else {
            homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(student, startDate, enddate);
        }


        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return new ModelAndView("homework");

    }

    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public ModelAndView updateStatusHomeWork(@RequestParam(value = "j_id") String id,
                                             @RequestParam(value = "j_status") String status,
                                             @RequestParam(value = "home_work_seach.j_sdt") String sdt,
                                             Model model) throws ParseException {

        HomeWorkStudentStatus homeWorkStudentStatus = homeWorkStudentStatusService.findById(Integer.parseInt(id));
        HomeWorkStatus newStatus = null;
        for (HomeWorkStatus h : HomeWorkStatus.values()) {
            if (h.toString().equals(status)) {
                newStatus = h;
            }
        }
        homeWorkStudentStatus.setStatus(newStatus);
        homeWorkStudentStatusService.saveOrUpdateHomeWorkStudentStatus(homeWorkStudentStatus);

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        //model.addAttribute("subject",subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        //model.addAttribute("hmstatus" ,status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sysdate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        /*model.addAttribute("end_date",dateFormat.format(sysdate.getTime()));
        model.addAttribute("start_date",dateFormat.format(startDate.getTime()));*/

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();
        homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(studentService.getStudentByLogin(user.getUsername()), startDate, sysdate);

        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return new ModelAndView("homework");

    }

    @RequestMapping("/homework")
    public String homework(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("login", user.getUsername());

        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject", subject_list);
        //Список предметов для отображения

        //Список статусов для отображения
        Set<String> status_list = new LinkedHashSet<>();
        status_list.add("Все");
        for (HomeWorkStatus s : HomeWorkStatus.values()) {
            status_list.add(s.toString());
        }
        model.addAttribute("hmstatus", status_list);
        //Список статусов для отображения

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sysdate = new GregorianCalendar();
        Calendar startDate = new GregorianCalendar();
        startDate.add(Calendar.DAY_OF_YEAR, -7);

        model.addAttribute("end_date", dateFormat.format(sysdate.getTime()));
        model.addAttribute("start_date", dateFormat.format(startDate.getTime()));

        List<HomeWorkStudentStatus> homeWorkStudentStatuses = new LinkedList<>();
        homeWorkStudentStatuses = homeWorkStudentStatusService.findByStudentDate(studentService.getStudentByLogin(user.getUsername()), startDate, sysdate);

        model.addAttribute("homeworks", homeWorkStudentStatuses);

        return "homework";
    }

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
        Map<Subject,Jurnal> j = new TreeMap<>() ;
        String strsdt = dateFormat.format(sdt.getTime());
        String stredt = dateFormat.format(edt.getTime());
        j = jurnalService.findByLoginForMap(user.getUsername(), sdt, edt);

        //---------------------------------------------
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        for (Map.Entry entry:
             j.entrySet()) {
            Subject subject = (Subject) entry.getKey();
            System.out.println(subject.getName());
            Jurnal jurnal= (Jurnal) entry.getValue();
            for (MarkStamp markStamp :
                 jurnal.getMarkStamps()) {
                String sDate = simpleDateFormat.format(markStamp.getDate().getTime());
                String sMark = markStamp.getMark().toString();
                System.out.println(sDate + " - "+sMark);
            }

        }

        List<Jurnal> jurnals = jurnalService.findByLogin(user.getUsername(),sdt,edt);
        List<Subject> subjectsT = subjectService.getAllSubjects();
        Collections.sort(subjectsT);
        String outtbl = "<tr>";
        for (Subject subject:subjectsT) {
            outtbl+="<td>"+subject.getName()+"</td>";
            int st = sdt.getTime().getDate();
            int en = edt.getTime().getDate();
            while (st<=en){
                outtbl+="<td>";
                for(Jurnal jurnal:jurnals){
                    for(MarkStamp markStamp:jurnal.getMarkStamps()){
                        if(jurnal.getSubject().equals(subject) && markStamp.getDate().getTime().getDate()==st){
                            outtbl+=markStamp.getMark().toString();
                        }
                    }
                }
                outtbl+="</td>";
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
        model.addAttribute("jurtab",outtbl);


        //Список предметов для отображения
        List<Subject> subjects = subjectService.getAllSubjects();
        Set<String> subject_list = new LinkedHashSet<>();
        subject_list.add("Все");
        for (Subject s : subjects) {
            subject_list.add(s.getName());
        }

        model.addAttribute("subject",subject_list);
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


        while (st<=en){
            String tmp = dateFormat.format(calendar.getTime());
            headerCalendar.add(tmp);
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            st++;
        }
        model.addAttribute("headerCalendar", headerCalendar);

        List<Jurnal> jurnals = jurnalService.findByLoginSubject(user.getUsername(),startDate,enddate,subject);

        String outtbl = "<tr>";

            outtbl+="<td>"+subject.getName()+"</td>";
            st = startDate.getTime().getDate();
            en = enddate.getTime().getDate();
            while (st<=en){
                outtbl+="<td>";
                for(Jurnal jurnal:jurnals){
                    for(MarkStamp markStamp:jurnal.getMarkStamps()){
                        if(jurnal.getSubject().equals(subject) && markStamp.getDate().getTime().getDate()==st){
                            outtbl+=markStamp.getMark().toString();
                        }
                    }
                }
                outtbl+="</td>";
                st++;
            }
            outtbl += "</tr>";

        model.addAttribute("jurtab",outtbl);


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

    @RequestMapping("/admin")
    public String admin(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return "admin";
    }

    @RequestMapping("admin/")
    public String admini(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        return "admin/index";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model){
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
}