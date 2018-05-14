package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.exceptions.InviteNotFoundException;
import ru.kpfu.forms.StudentRegistrationForm;
import ru.kpfu.forms.TeacherRegistrationForm;
import ru.kpfu.services.RegistrationService;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/signUp/student", method = RequestMethod.GET)
    public String getStudentRegistrationPage(ModelMap map) {
        map.put("form", new StudentRegistrationForm());
        map.put("classes", registrationService.getClasses());
        return "student_registration";
    }

    @RequestMapping(value = "/signUp/student", method = RequestMethod.POST)
    public String registerStudent(@Valid @ModelAttribute("form") StudentRegistrationForm form, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp/student";
        }
        try {
            registrationService.registerStudent(form);
        } catch (DuplicateKeyException | InviteNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/signUp/student";
        }

        return "redirect:/login";
    }

    @RequestMapping(value = "/signUp/teacher", method = RequestMethod.GET)
    public String getTeacherRegistrationPage(ModelMap map) {
        map.put("form", new TeacherRegistrationForm());
        return "teacher_registration";
    }

    @RequestMapping(value = "/signUp/teacher", method = RequestMethod.POST)
    public String registerTeacher(@Valid @ModelAttribute("form") TeacherRegistrationForm form, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors().get(0).getDefaultMessage());
            return "redirect:/signUp/student";
        }
        try {
            registrationService.registerTeacher(form);
        } catch (DuplicateKeyException | InviteNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/signUp/student";
        }

        return "redirect:/login";
    }
}
