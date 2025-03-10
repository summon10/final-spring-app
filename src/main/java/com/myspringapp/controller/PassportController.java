package com.myspringapp.controller;

import com.myspringapp.service.PassportService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassportController {

    private final PassportService passportServiceImpl;

    public PassportController(PassportService passportService) {
        this.passportServiceImpl = passportService;
    }


    /*@GetMapping("/")
    public String welcome(Model model) {

        return "passport";
    }*/

    @GetMapping("/passport")
    public String passportWelcome(Model model) {

        return "passport";
    }

    @GetMapping("/passportView")
    public String passportView(Model model) {

        return "passportView";
    }

    @GetMapping("/passportEdit")
    @PreAuthorize("hasAuthority('ROLE_EMPLOYEE')")
    public String passportEdit(Model model) {

        return "passportEdit";
    }

    @GetMapping("/passportNew")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String passportNew(Model model) {

        return "passportNew";
    }

}
