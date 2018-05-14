package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.dtos.LessonDto;
import ru.kpfu.forms.AddHomeworkForm;
import ru.kpfu.forms.AddMarkForm;
import ru.kpfu.models.Homework;
import ru.kpfu.models.Lesson;
import ru.kpfu.services.DateUtil;
import ru.kpfu.services.TeacherService;

import java.security.Principal;
import java.util.List;

@Controller
public class TeacherController {
    private final TeacherService teacherService;
    private final DateUtil dateUtil;

    @Autowired
    public TeacherController(TeacherService teacherService, DateUtil dateUtil) {
        this.teacherService = teacherService;
        this.dateUtil = dateUtil;
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @RequestMapping(value = "/teacher", method = RequestMethod.GET)
    public String teacherRoot(){
        return "redirect:/teacher/timetable";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @RequestMapping(value = "/teacher/timetable", method = RequestMethod.GET)
    public String getTimetable(ModelMap modelMap, Principal principal){
        modelMap.put("lessons", teacherService.getTimetable(principal.getName()));
        return "teacher_timetable";
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @RequestMapping(value = "/teacher/lesson", method = RequestMethod.GET)
    public String getLessonPage(ModelMap modelMap, Principal principal, RedirectAttributes redirectAttributes,
                                @RequestParam(value = "date", required = false) String date,
                                @RequestParam(value = "lessonId", required = false) String lessonId){
        String login = principal.getName();
        if(date == null || date.isEmpty()) {
            redirectAttributes.addAttribute("date", dateUtil.convertCurrentDateToString(DateUtil.STRING_DATE_TYPE_ISO));
            return "redirect:/teacher/lesson";
        } else {
            List<LessonDto> lessons = teacherService.getLessonsByDate(login,
                    dateUtil.convertFromString(DateUtil.STRING_DATE_TYPE_ISO, date));
            modelMap.put("lessons", lessons);
            modelMap.put("date", date);
            if(lessonId != null && !lessonId.isEmpty()) {
                modelMap.put("lessonId", lessonId);
                modelMap.put("homework", teacherService.getHomeworkByLessonIdAndDate(lessonId,
                        dateUtil.convertFromString(DateUtil.STRING_DATE_TYPE_ISO, date)));
                modelMap.put("students", teacherService.getStudentsByLesson(lessonId));
            }
            System.out.println(date);
            System.out.println(lessonId);
            return "teacher_lesson";
        }
    }

    @PreAuthorize("hasAuthority('TEACHER')")
    @RequestMapping(value = "/teacher/lesson", method = RequestMethod.POST)
    public String addMarkOrHomework(RedirectAttributes redirectAttributes,
                                    @RequestParam(value = "date", required = false) String date,
                                    @RequestParam(value = "lessonId", required = false) String lessonId,
                                    @ModelAttribute("mark_form") AddMarkForm addMarkForm,
                                    @ModelAttribute("homework_form") AddHomeworkForm addHomeworkForm){
        if(!(addHomeworkForm.getHomeworkText() == null || addHomeworkForm.getHomeworkText().isEmpty())) {
            teacherService.saveHomework(addHomeworkForm);
        } else if(!(addMarkForm.getStudentId() == null || addMarkForm.getStudentId().isEmpty()
                || addMarkForm.getValue() == null || addMarkForm.getValue().isEmpty())) {
            teacherService.saveMark(addMarkForm);
        }

        redirectAttributes.addAttribute("date", date);
        redirectAttributes.addAttribute("lessonId", lessonId);
        return "redirect:/teacher/lesson";
    }
}
