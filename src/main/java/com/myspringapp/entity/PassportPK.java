package com.myspringapp.entity;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Embeddable
@Data
public class PassportPK implements Serializable {

    @Digits(integer = 4, fraction = 0)
    private Integer seria;
    @Digits(integer = 6, fraction = 0)
    private Integer number;
}
