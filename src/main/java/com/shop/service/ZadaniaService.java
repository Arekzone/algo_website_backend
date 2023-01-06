package com.shop.service;

import com.shop.api.model.ZadanieBody;
import com.shop.model.Zadania;
import com.shop.model.repository.ZadaniaDAO;
import org.springframework.stereotype.Service;

@Service
public class ZadaniaService {

    private final ZadaniaDAO zadaniaDAO;

    public ZadaniaService(ZadaniaDAO zadaniaDAO) {
        this.zadaniaDAO = zadaniaDAO;
    }

    public void handleForm(ZadanieBody zadanieBody) {
        Zadania zadania = new Zadania();
        zadania.setText(zadanieBody.getText());
        zadania.setChecked(zadanieBody.isChecked());
        zadania.setWynikUzytkownika(zadanieBody.getWynikUzytkownika());
        zadania.setPoprawnyWynik(zadanieBody.getPoprawnyWynik());
        zadania.setNazwaZadania(zadanieBody.getNazwaZadania());
        zadania.setKategoria(zadanieBody.getKategoria());
        zadaniaDAO.save(zadania);
    }
}
