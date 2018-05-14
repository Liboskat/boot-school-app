package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.services.DateUtil;
import ru.kpfu.services.StudentService;

import java.security.Principal;
import java.util.Date;

@Controller
public class StudentController {
    private final StudentService studentService;
    private final DateUtil dateUtil;

    @Autowired
    public StudentController(StudentService studentService, DateUtil dateUtil) {
        this.studentService = studentService;
        this.dateUtil = dateUtil;
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String studentRoot(){
        return "redirect:/student/timetable";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping(value = "/student/timetable", method = RequestMethod.GET)
    public String getTimetable(ModelMap modelMap, Principal principal){
        modelMap.put("lessons", studentService.getTimetable(principal.getName()));
        return "student_timetable";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping(value = "/student/diary", method = RequestMethod.GET)
    public String getDiary(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("week", dateUtil.toWeek(new Date()));
        return "redirect:/student/diary/{week}";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @RequestMapping(value = "/student/diary/{week}", method = RequestMethod.GET)
    public String getDiaryByWeek(RedirectAttributes redirectAttributes, ModelMap modelMap, Principal principal,
                                 @PathVariable("week") Integer week){
        if(week < 1) {
            redirectAttributes.addAttribute("week", dateUtil.getMaximumWeek(new Date()));
            return "redirect:/student/diary/{week}";
        } else if (week > dateUtil.getMaximumWeek(new Date())) {
            redirectAttributes.addAttribute("week", 1);
            return "redirect:/student/diary/{week}";
        }
        modelMap.put("week", week);
        modelMap.put("diary", studentService.getDiary(principal.getName(), week));
        return "student_diary";
    }

}
