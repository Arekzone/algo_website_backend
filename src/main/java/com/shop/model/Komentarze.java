package com.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Komentarze {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String komentarz;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnore
    private Zadania zadania;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "local_user_id")
    private LocalUser localUser;

    public LocalUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(LocalUser localUser) {
        this.localUser = localUser;
    }


    public Zadania getZadania() {
        return zadania;
    }

    public void setZadania(Zadania zadania) {
        this.zadania = zadania;
    }
}
