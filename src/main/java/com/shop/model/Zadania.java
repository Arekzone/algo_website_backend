package com.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Zadania {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private boolean checked;
        @NotNull
        private String text;
        @NotNull
        private String wynikUzytkownika;
        @NotNull
        private String poprawnyWynik;
        @NotNull
        private String kategoria;
        @NotNull
        private String nazwaZadania;


        @JsonIgnore
        @ManyToOne(cascade = CascadeType.REMOVE)
        @JoinColumn(name = "local_user_id")
        private LocalUser localUser;

}
