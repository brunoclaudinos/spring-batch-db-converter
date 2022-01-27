package com.brunoclaudinos.dbconverter.domain.old.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "player")
public class PlayerOld {

    @Id
    private Long id;
    private String description;
    private String birthdate;
}
