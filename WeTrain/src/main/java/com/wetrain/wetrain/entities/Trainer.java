package com.wetrain.wetrain.entities;

import java.time.LocalDate;

public class Trainer extends User {

    public Trainer(String name, LocalDate dateOfBirth, String fc, String email){
        super(name, dateOfBirth, fc, email);
    }
}