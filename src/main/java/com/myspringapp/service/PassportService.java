package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;

import java.util.Date;
import java.util.List;

public interface PassportService {
    List<Passport> getAllPassports();
    void savePassport(Passport passport);
    void deletePassport(PassportPK passportPK);
    Passport getPassportById(PassportPK passportPK);
    List <Passport> getPassportsByNames(String name, String surname, String secondName);
    List <Passport> getPassportsByConviction(Boolean conviction);
    List <Passport> getPassportsBySurname(String surname);
    List <Passport> getPassportsByBirthdayToday();
}
