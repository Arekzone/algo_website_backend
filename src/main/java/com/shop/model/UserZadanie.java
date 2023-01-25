package com.shop.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.config.Task;

@Entity
@Getter
@Setter
public class UserZadanie{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "localuser_id")
    private LocalUser user;

    @ManyToOne
    @JoinColumn(name = "zadania_id")
    private Zadania task;

    private String wynik_uzytkownika;

    // getters and setters
}