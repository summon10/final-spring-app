package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import com.myspringapp.repository.PassportRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<Passport> getPassportsByNames(String name, String surname, String secondName) {
        return passportRepo.findPassportsByFIO(name, surname, secondName);
    }

    @Override
    public List<Passport> getPassportsByConviction(Boolean conviction) {
        return passportRepo.findPassportsByConviction(conviction);
    }

    @Override
    public List<Passport> getPassportsBySurname(String surname) {
        return passportRepo.findAllBySurname(surname);
    }

    @Override
    public List<Passport> getPassportsByBirthdayToday() {
        LocalDate currentDate = LocalDate.now();
        return passportRepo.getPassportsByBirthDate(currentDate);
    }
}
