package com.myspringapp.controller;

import com.myspringapp.service.GetPassportPage;
import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.service.PassportService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class PassportController {

    private final PassportService passportServiceImpl;
    private final GetPassportPage getPassportPage;

    public PassportController(PassportService passportService, GetPassportPage getPassportPage) {
        this.passportServiceImpl = passportService;

        this.getPassportPage = getPassportPage;
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
    public String passportViewGetAll(Model model,
                                     @RequestParam("page") Optional<Integer> page,
                                     @RequestParam("size") Optional<Integer> size
    ) {

        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getAllPassports());
        model.addAttribute("passportPage", passportPage);

        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);

        return "passportView";
    }

    @GetMapping("/passportView/getByHavingFamily")
    public String passportViewGetByHavingFamily(Model model,
                                                @RequestParam("page") Optional<Integer> page,
                                                @RequestParam("size") Optional<Integer> size) {

        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getPassportsByHavingFamily(true));
        model.addAttribute("passportPage", passportPage);
        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);

        return "passportView";
    }

    @GetMapping("/passportView/getByHavingConviction")
    public String passportViewGetByHavingConviction(Model model,
                                                     @RequestParam("page") Optional<Integer> page,
                                                    @RequestParam("size") Optional<Integer> size) {
        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getPassportsByHavingConviction(true));
        model.addAttribute("passportPage", passportPage);
        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);

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
                                        @Valid @RequestParam(value = "surname", required = true) String surname,
                                           @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size)

     {

        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getPassportsBySurname(surname));
        model.addAttribute("passportPage", passportPage);
        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);
        return "passportView";
    }

    @PostMapping("/passportView/getByFIO")
    public String passportViewGetByFIO(Model model,
                                           @Valid @RequestParam(value = "fullName", required = true) String fullName,
                                            @RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size
    ) {
        String[] parts = fullName.split("[,\\s]+");
        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getPassportsByNames(parts));
        model.addAttribute("passportPage", passportPage);
        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);
        return "passportView";
    }

    @PostMapping("/passportView/getByCity")
    public String passportViewGetByCity(Model model,
                                           @Valid @RequestParam(value = "city", required = true) String city,
                                           @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size
    ) {
        Page <Passport> passportPage = getPassportPage.getPage(page,size,passportServiceImpl.getPassportsByCity(city));
        model.addAttribute("passportPage", passportPage);
        List <Integer> pageNumbers = getPassportPage.getPageCount(passportPage);
        model.addAttribute("pageNumbers", pageNumbers);

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

    @GetMapping("/passportDelete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String passportDelete(@PathVariable(value = "id") String id,
                               Model model,
                               Passport passport
    ) {
        String[] parts = id.split("[,\\s]+");
        PassportPK passportPK = new PassportPK();
        passportPK.setSeria(Integer.parseInt(parts[0]));
        passportPK.setNumber(Integer.parseInt(parts[1]));

        passportServiceImpl.deletePassport(passportPK);
        System.out.println("Deleted successfully");
        return "passportView";
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

        passportServiceImpl.savePassport(passport);

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
