package com.myspringapp.service;

import com.myspringapp.entity.Passport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class GetPassportPage {

    private final PassportService passportServiceImpl;

      public GetPassportPage(PassportService passportServiceImpl) {
        this.passportServiceImpl = passportServiceImpl;
    }

    public Optional<Integer> size = Optional.empty();

        public Page<Passport> getPage(Optional<Integer> page, List<Passport> passports) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page <Passport> passportPage;
        return passportPage = findPaginated(PageRequest.of(currentPage - 1, pageSize),passports);
    }

        public List<Integer> getPageCount (Page<Passport> passportPage) {
            int totalPages = passportPage.getTotalPages();
            List<Integer> pageNumbers = List.of();
            if (totalPages > 0) {
                pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());

            }
            return pageNumbers;
        }
    public Page<Passport> findPaginated(Pageable pageable, List<Passport> passports) {

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
