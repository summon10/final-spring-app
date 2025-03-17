package com.myspringapp.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.Length;

import java.util.Date;


@Entity
public class Passport {

    @EmbeddedId
    @NotEmpty(message = "Seria and Number required")
    private PassportPK passportPK;

    @Length(max = 45, message = "Too long Name")
    @NotEmpty
    private String name;

    @Length(max = 45, message = "Too long Surname")
    @NotEmpty
    private String surname;

    @Length(max = 45, message = "Too long Second Name")
    @NotEmpty
    private String secondName;

    @Length(max = 45, message = "Too long City Name")
    private String city;

    Boolean gender;
    Boolean family;
    Boolean conviction;

    @NotEmpty
    @Past
    Date birthDate;


}
