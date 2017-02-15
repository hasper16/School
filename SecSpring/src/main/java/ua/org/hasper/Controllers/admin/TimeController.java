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
import ua.org.hasper.Entity.ScheduleTimes;
import ua.org.hasper.service.ScheduleTimesService;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class TimeController {
    @Autowired
    private ScheduleTimesService scheduleTimesService;
    int PAGESIZE = 25;
    int curPage = 0;

    @RequestMapping("admin/times/add")
    public ModelAndView add(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());

        return new ModelAndView("admin/times/add");
    }

    @RequestMapping(value = "admin/times/add", method = RequestMethod.POST)
    public ModelAndView newSubject(@RequestParam(value = "j_duration") Integer duration,
                                   @RequestParam(value = "j_lessonNum") Integer lessonNum,
                                   @RequestParam(value = "j_sdt") String sdt,
                                   Model model) {
        int alert = 0;
        String message = "";
        try {
            int hh = Integer.parseInt(sdt.substring(0, 2));
            int mm = Integer.parseInt(sdt.substring(3, 5));

            Calendar calSdt = new GregorianCalendar(0, 0, 0, hh, mm);
            List<ScheduleTimes> dublicat = scheduleTimesService.findByDurationAndLessonNumAndSdt(duration, lessonNum, calSdt);
            if (dublicat.size() > 0) {
                alert = 2;
                message = "Такой урок уже существует";
            } else {
                ScheduleTimes scheduleTimes = new ScheduleTimes(lessonNum, calSdt, duration);
                scheduleTimesService.addOrUpdateScheduleTimes(scheduleTimes);
                alert = 1;
                message = "Урок " + scheduleTimes.getLessonNum() + ") " + scheduleTimes.getSdt() + " " + scheduleTimes.getDurationMin() + " мин. успешно добавлен";
            }
        } catch (Exception e) {
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return add(model);
    }

    @RequestMapping("admin/times/list")
    public ModelAndView list(Model model) {
        return listFilter(0, model);
    }

    @RequestMapping(value = "admin/times/list", params = {"page"})
    public ModelAndView listFilter(@RequestParam(value = "page") int page, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        curPage = page;

        Page<ScheduleTimes> scheduleTimesPage = scheduleTimesService.getAll(curPage, PAGESIZE);
        int totalPages = scheduleTimesPage.getTotalPages() - 1;
        List<ScheduleTimes> scheduleTimes = scheduleTimesPage.getContent();
        model.addAttribute("scheduleTimes", scheduleTimes);

        return new ModelAndView("admin/times/list");
    }

    @RequestMapping("/times/delete")
    public ModelAndView deleteSubject(@RequestParam(value = "id") int id, Model model) {
        int alert=0;
        String message = "";
        try {
            ScheduleTimes scheduleTimes = scheduleTimesService.findById(id);
            scheduleTimesService.delScheduleTimes(scheduleTimes);
            alert = 1;
            message = "Урок " + scheduleTimes.getLessonNum() + ") " + scheduleTimes.getSdt() + " " + scheduleTimes.getDurationMin() + " мин. успешно удален";

        }catch (Exception e){
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return listFilter(curPage, model);
    }


    @RequestMapping("/times/edit")
    public ModelAndView editSubject(@RequestParam(value = "id") int id,
                                    @RequestParam(value = "j_duration") Integer duration,
                                    @RequestParam(value = "j_lessonNum") Integer lessonNum,
                                    @RequestParam(value = "j_sdt") String sdt,
                                    Model model) {
        int alert = 0;
        String message = "";
        try {
            int hh = Integer.parseInt(sdt.substring(0, 2));
            int mm = Integer.parseInt(sdt.substring(3, 5));

            Calendar calSdt = new GregorianCalendar(0, 0, 0, hh, mm);

            List<ScheduleTimes> dublicat = scheduleTimesService.findByDurationAndLessonNumAndSdt(duration, lessonNum, calSdt);
            ScheduleTimes scheduleTimes = scheduleTimesService.findById(id);

            if(dublicat.size()>0){
                for (ScheduleTimes s:
                     dublicat) {
                    if(s.getId()!=scheduleTimes.getId()){
                        alert=2;
                    }
                }
            }
            if(alert!=2) {
                scheduleTimes.setDurationMin(duration);
                scheduleTimes.setLessonNum(lessonNum);
                scheduleTimes.setSdt(calSdt);

                scheduleTimesService.addOrUpdateScheduleTimes(scheduleTimes);

                alert=1;
                message = "Урок " + scheduleTimes.getLessonNum() + ") " + scheduleTimes.getSdt() + " " + scheduleTimes.getDurationMin() + " мин. успешно изменен";
            }
        }catch (Exception e){
            alert = 3;
            message = "Ошибка (" + e.getMessage() + ")";
        }

        model.addAttribute("alert", alert);
        model.addAttribute("message", message);
        return listFilter(curPage, model);
    }
}
