package com.starwars.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Account {

    private Long id;
    private String username;
    private String password;
}
