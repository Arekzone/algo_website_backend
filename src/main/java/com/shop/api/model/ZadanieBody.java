package com.shop.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZadanieBody {
    private String text;
    private boolean checked;
    private String wynikUzytkownika;
    private String poprawnyWynik;
    private String nazwaZadania;
    private String kategoria;
    private String trescSkryptu;
    private String odpowiedz;
}
