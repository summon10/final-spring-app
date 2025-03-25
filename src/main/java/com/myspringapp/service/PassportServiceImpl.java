package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.repository.PassportRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PassportServiceImpl implements PassportService {

    private final PassportRepo passportRepo;

    public PassportServiceImpl(PassportRepo passportRepo) {
        this.passportRepo = passportRepo;
    }

    @Override
    public List<Passport> getAllPassports() {
        return passportRepo.findAll();
    }

    @Override
    public void savePassport(Passport passport) {
        passportRepo.save(passport);
    }

    @Override
    public void deletePassport(PassportPK passportPK) {
        passportRepo.deleteById(passportPK);
    }

    @Override
    public Passport getPassportById(PassportPK passportPK) {

        Optional<Passport> optionalPassport = passportRepo.findById(passportPK);
        Passport passport;
        if (optionalPassport.isPresent()) {
            passport = optionalPassport.get();

        }
        else  throw new RuntimeException ("Passport not found!");

        return passport;
    }

    @Override
    public List<Passport> getPassportsByNames(String ...names) {

        List<String> validNames = Arrays.stream(names).toList();
        switch(validNames.size()) {
            case 0: throw new RuntimeException ("Passport not found!");
            case 1: return passportRepo.findPassportsByName(validNames.get(0));
            case 2: return passportRepo.findPassportsByFI(validNames.get(0), validNames.get(1));
            default: return passportRepo.findPassportsByFIO(validNames.get(0), validNames.get(1), validNames.get(2));
        }

    }

    public List<Passport> getPassportsByCity(String city) {
        return passportRepo.getPassportsByCity(city);
    }

    @Override
    public List<Passport> getPassportsByHavingFamily(Boolean havingFamily) {
        return passportRepo.getPassportsByFamily(havingFamily);
    }

    @Override
    public List<Passport> getPassportsByHavingConviction(Boolean havingConviction) {
        return passportRepo.findPassportsByConviction(havingConviction);
    }


    @Override
    public List<Passport> getPassportsBySurname(String surname) {
        return passportRepo.findAllBySurname(surname);
    }

    @Override
    public List<Passport> getPassportsByName(String name) {
        return passportRepo.findAllByName(name);
    }

    @Override
    public List<Passport> getPassportsByBirthdayToday() {
        LocalDate currentDate = LocalDate.now();
        return passportRepo.getPassportsByBirthDate(currentDate);
    }
    @Override
    public Page<Passport> findPaginated(Pageable pageable, List<Passport> passports) {
        //List<Passport> passports = passportRepo.findAll();

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Passport> list;

        if (passports.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, passports.size());
            list = passports.subList(startItem, toIndex);
        }

        Page<Passport> passportPage
                = new PageImpl<Passport>(list, PageRequest.of(currentPage, pageSize), passports.size());

        return passportPage;
    }
}
