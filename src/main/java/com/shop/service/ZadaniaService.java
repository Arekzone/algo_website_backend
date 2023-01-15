package com.shop.service;

import com.shop.api.model.ZadanieBody;
import com.shop.model.Zadania;
import com.shop.model.ZadaniaUser;
import com.shop.model.repository.ZadaniaDAO;
import com.shop.model.repository.ZadaniaUserDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZadaniaService {

    private final ZadaniaDAO zadaniaDAO;
    private final ZadaniaUserDao zadaniaUserDao;

    public ZadaniaService(ZadaniaDAO zadaniaDAO, ZadaniaUserDao zadaniaUserDao) {
        this.zadaniaDAO = zadaniaDAO;
        this.zadaniaUserDao = zadaniaUserDao;
    }

    public void handleForm(ZadanieBody zadanieBody) {
        Zadania zadania = new Zadania();
        zadania.setText(zadanieBody.getText());
        zadania.setChecked(zadanieBody.isChecked());
        zadania.setWynikUzytkownika(zadanieBody.getWynikUzytkownika());
        zadania.setTrescSkryptu(zadanieBody.getTrescSkryptu());
        zadania.setPoprawnyWynik(zadanieBody.getPoprawnyWynik());
        zadania.setNazwaZadania(zadanieBody.getNazwaZadania());
        zadania.setKategoria(zadanieBody.getKategoria());
        zadaniaDAO.save(zadania);
        ZadaniaUser zadaniaUser = new ZadaniaUser();
        List<Zadania> zadaniaList = new ArrayList<>();
        List<Zadania> findAll = zadaniaDAO.findAll();
        for(Zadania zadania1:findAll){
            zadaniaList.add(zadania1);
        }
        zadaniaUser.setZadania(zadaniaList);
        zadaniaUserDao.save(zadaniaUser);
    }
}
