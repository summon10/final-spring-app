package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PassportPageService {

    final private List<Passport> passports;


    public PassportPageService(List<Passport> passports) {
        this.passports = passports;
    }


}
