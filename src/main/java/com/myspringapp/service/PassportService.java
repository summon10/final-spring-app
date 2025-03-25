package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface PassportService {
    List<Passport> getAllPassports();
    void savePassport(Passport passport);
    void deletePassport(PassportPK passportPK);
    Passport getPassportById(PassportPK passportPK);
    List <Passport> getPassportsByNames(String ...names);
    List <Passport> getPassportsByName(String name);
    List <Passport> getPassportsBySurname(String surname);
    List <Passport> getPassportsByBirthdayToday();
    List <Passport> getPassportsByCity(String city);
    List <Passport> getPassportsByHavingFamily(Boolean havingFamily);

    List<Passport> getPassportsByHavingConviction(Boolean havingConviction);
    Page<Passport> findPaginated(Pageable pageable, List<Passport> passports);
}
