package com.myspringapp.controller;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.service.PassportService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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

        return "/passportView";
    }

    @PostMapping("/passportView")
    public String passportViewChange(Model model, @RequestParam(value = "action") String action) {
        System.out.println(action);
        return "redirect:/passportView/" + action;
    }

    @GetMapping("/passportView/getAll")
    public String passportViewGetAll(Model model) {
        List<Passport> passports = passportServiceImpl.getAllPassports();
        model.addAttribute("passports", passports);
      //  System.out.println(passports);
        return "passportView";
    }

    @PostMapping("/passportView/getById")
    public String passportViewGetById(Model model,
                                     @Valid @RequestParam(value = "seria", required = true) Integer seria,
                                      @Valid @RequestParam(value = "number", required = true) Integer number,
                                      @Valid @ModelAttribute("passportPK") PassportPK passportPK,
                                      @Valid @ModelAttribute("passportPK") Passport passport,
                                      BindingResult bindingResult
                                      ) {
        if (bindingResult.hasErrors()) {
            return "passportView";
        }
        passportPK.setNumber(number);
        passportPK.setSeria(seria);
        passport= passportServiceImpl.getPassportById(passportPK);
        model.addAttribute("passports", passport);
       // System.out.println(passport);
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
