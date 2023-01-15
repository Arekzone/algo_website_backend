package com.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Zadania{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
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
        @NotNull
        private String trescSkryptu;
        @ManyToMany(mappedBy = "zadania")
        private List<ZadaniaUser> zadaniaUsers;
        @OneToMany
        private List<Komentarze> komentarzes;

}
