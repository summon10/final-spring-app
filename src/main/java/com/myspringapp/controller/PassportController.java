package com.myspringapp.controller;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.service.PassportService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/passportView/getByHavingFamily")
    public String passportViewGetByHavingFamily(Model model) {
        List<Passport> passports = passportServiceImpl.getPassportsByHavingFamily(true);
        model.addAttribute("passports", passports);

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

        return "passportView";
    }


     @PostMapping("/passportView/getBySurname")
    public String passportViewGetBySurname(Model model,
                                        @Valid @RequestParam(value = "surname", required = true) String surname

    ) {

        List <Passport> passports= passportServiceImpl.getPassportsBySurname(surname);
        model.addAttribute("passports", passports);
        System.out.println(passports.toString());
        return "passportView";
    }

    @PostMapping("/passportView/getByFIO")
    public String passportViewGetByFIO(Model model,
                                           @Valid @RequestParam(value = "fullName", required = true) String fullName

    ) {
        String[] parts = fullName.split("[,\\s]+");
        List <Passport> passports= passportServiceImpl.getPassportsByNames(parts);
        model.addAttribute("passports", passports);
        System.out.println(passports.toString());
        return "passportView";
    }

    @PostMapping("/passportView/getByCity")
    public String passportViewGetByCity(Model model,
                                           @Valid @RequestParam(value = "city", required = true) String city
    ) {
        List <Passport> passports= passportServiceImpl.getPassportsByCity(city);
        model.addAttribute("passports", passports);
        System.out.println(passports.toString());
        return "passportView";
    }

    @GetMapping("/passportEdit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String passportEdit(@PathVariable(value = "id") String id,
                               Model model,
                               Passport passport
                              ) {
        String[] parts = id.split("[,\\s]+");
        PassportPK passportPK = new PassportPK();
        passportPK.setSeria(Integer.parseInt(parts[0]));
        passportPK.setNumber(Integer.parseInt(parts[1]));

        passport = passportServiceImpl.getPassportById(passportPK);
        model.addAttribute("passport", passport);
        System.out.println(passport);
        return "passportEdit";
    }

    @PostMapping("/passportEdit")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public String passportUpdate(@Valid @ModelAttribute ("passport") Passport passport,
                                 @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate,
                                 BindingResult result,
                                 Model model)
    {
        if (result.hasErrors()) {
            return "passportEdit";
        }

        System.out.println(birthDate);
        System.out.println(passport);
       passportServiceImpl.savePassport(passport);
        System.out.println("Edition Success");
        return "passportView";
    }


    @GetMapping("/passportNew")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String passportNew(Model model) {
        Passport passport = new Passport();
        model.addAttribute("passport", passport);
        return "passportNew";
    }

    @PostMapping("/passportNew")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String passportCreateNew(@ModelAttribute("passport") @Valid Passport passport,
                                    Model model,
                                    BindingResult result) {
        if (result.hasErrors()) {
            return "passport";
        }

        model.addAttribute("passport", passport);
        System.out.println(passport);
        passportServiceImpl.savePassport(passport);
        return "passport";
    }


}
