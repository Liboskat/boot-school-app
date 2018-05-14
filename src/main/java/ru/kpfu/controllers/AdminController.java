package ru.kpfu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.security.roles.UserRole;
import ru.kpfu.services.InviteService;


@Controller
public class AdminController {
    private final InviteService inviteService;

    @Autowired
    public AdminController(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminRoot(){
        return "redirect:/admin/invite";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/invite", method = RequestMethod.GET)
    public String getInvitePage(ModelMap modelMap, @RequestParam(value = "invite_code", required = false) String inviteCode){
        modelMap.put("invite_code", inviteCode);
        return "admin_invite";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin/invite", method = RequestMethod.POST)
    public String generateInvite(RedirectAttributes redirectAttributes, @RequestParam("role") UserRole role) {
        String inviteCode = inviteService.generate(role);
        redirectAttributes.addAttribute("invite_code", inviteCode);
        return "redirect:/admin/invite";
    }
}
