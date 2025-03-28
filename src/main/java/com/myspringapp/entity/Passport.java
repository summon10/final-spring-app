package com.myspringapp.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Passport {

    public Passport(PassportPK passportPK, String name, String surname, String secondName, String city,
                    Character gender,
                    Boolean family, Boolean conviction, LocalDate birthDate) {
        this.passportPK = passportPK;
        this.name = name;
        this.surname = surname;
        this.secondName = secondName;
        this.city = city;
        this.gender = gender;
        this.family = family;
        this.conviction = conviction;
        this.birthDate = birthDate;
    }

    @EmbeddedId

    private PassportPK passportPK;

    @Length(max = 45, message = "Too long Name")
    @NotEmpty (message = "Имя не должно быть пустым!")
    private String name;

    @Length(max = 45, message = "Too long Surname")
    @NotEmpty
    private String surname;

    @Length(max = 45, message = "Too long Second Name")

    private String secondName;

    @Length(max = 45, message = "Too long City Name")
    private String city;

    private Character gender;
    private Boolean family;
    private Boolean conviction;

    @NotNull
    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;


}
