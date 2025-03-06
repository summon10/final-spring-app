package com.myspringapp.repository;

import com.myspringapp.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepo extends JpaRepository<Passport, Integer> {
}
