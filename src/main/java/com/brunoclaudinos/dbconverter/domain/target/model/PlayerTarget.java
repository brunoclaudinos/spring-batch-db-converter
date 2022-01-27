package com.brunoclaudinos.dbconverter.domain.target.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "player")
public class PlayerTarget {

    @Id
    private Long id;
    private String name;
    private LocalDate birthdate;
}
