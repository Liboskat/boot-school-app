package ru.kpfu.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.security.roles.UserRole;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root(Authentication authentication) {
        if(authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.ADMIN.toString()))) {
            return "redirect:/admin";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.STUDENT.toString()))) {
            return "redirect:/student";
        } else if(authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.TEACHER.toString()))) {
            return "redirect:/teacher";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Authentication authentication, @RequestParam(value = "error", required = false)String error, ModelMap map) {
        map.put("error", error);
        if (authentication != null) {
            return "redirect:/";
        }
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Authentication authentication) {
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "redirect:/login";
    }
}
