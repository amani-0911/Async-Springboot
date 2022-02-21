package com.ex1mcs.microser.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String gender;
}
