package com.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
public class Zadania{
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private boolean checked;
        @NotNull
        private String text;
        private String wynikUzytkownika;
        @NotNull
        private String poprawnyWynik;
        @NotNull
        private String kategoria;
        @NotNull
        private String nazwaZadania;
        @OneToMany
        private List<Komentarze> komentarzes;

        @Column(name = "odpowiedz", length = 10000)
        private String odpowiedz;


}
