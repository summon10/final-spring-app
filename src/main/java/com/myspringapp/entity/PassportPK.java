package com.myspringapp.entity;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Embeddable

public class PassportPK implements Serializable {

    @Digits(integer = 4, fraction = 0)
    @NotNull(message = "Seria and Number required")
    private Integer seria;

    @Digits(integer = 6, fraction = 0)
    @NotNull(message = "Seria and Number required")
    private Integer number;

    public Integer getSeria() {
        return seria;
    }

    @Override
    public String toString() {
        return seria + " " + number;

    }

    public void setSeria(Integer seria) {
        this.seria = seria;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
