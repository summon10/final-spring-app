package com.myspringapp.repository;

import com.myspringapp.entity.Passport;
import com.myspringapp.entity.PassportPK;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PassportRepo extends JpaRepository<Passport, PassportPK> {

    @Query(value = "SELECT  p FROM Passport p WHERE p.name = :name AND p.surname = :surname AND p.secondName = :second_name")
    List<Passport> findPassportsByFIO(@Param("name") String name, @Param("surname") String surname, @Param("second_name") String second_name);

    @Query(value = "SELECT  p FROM Passport p WHERE p.name = :name AND p.surname = :surname")
    List<Passport> findPassportsByFI(@Param("name") String name, @Param("surname") String surname);

    List<Passport> findPassportsByConviction(Boolean conviction);

    List<Passport> findAllBySurname(@Length(max = 45, message = "Too long Surname") @NotEmpty String surname);

    List<Passport> getPassportsByBirthDate(LocalDate birthDate);

    List<Passport> findAllByName(@Length(max = 45, message = "Too long Name") @NotEmpty String name);

    List<Passport> findPassportsByName(@Length(max = 45, message = "Too long Name") @NotEmpty String name);

    List<Passport> getPassportsByCity(@Length(max = 45, message = "Too long City Name") String city);

    List<Passport> getPassportsByFamily(Boolean family);
}
