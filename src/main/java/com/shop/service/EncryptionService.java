package com.shop.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    //wartosc zmiennej okreslona w application.properties
    @Value("${encryption.salt.rounds}")
    private int saltRounds;
    private String salt;

    //uruchamia tę metode automatycznie po stworzeniu beana albo innej metody autowired
    @PostConstruct
    public void postConstruct(){
        salt = BCrypt.gensalt(saltRounds);
    }
    public String encryptPassword(String password){
        return BCrypt.hashpw(password,salt);
    }
    public boolean verifyPassword(String password, String hash){
        return BCrypt.checkpw(password,hash);
    }
}
