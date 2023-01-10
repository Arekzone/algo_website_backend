package com.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.model.LocalUser;
import com.shop.model.Zadania;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ZadaniaUser{
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JsonIgnore
    @JoinColumn(name = "zadania_id")
    private List<Zadania> zadania;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private LocalUser user;
}
