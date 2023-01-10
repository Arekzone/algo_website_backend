package com.shop.service;

import com.shop.api.model.LoginBody;
import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.model.LocalUser;
import com.shop.model.Zadania;
import com.shop.model.ZadaniaUser;
import com.shop.model.repository.LocalUserDao;
import com.shop.model.repository.ZadaniaDAO;
import com.shop.model.repository.ZadaniaUserDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private LocalUserDao localUserDao;
    private EncryptionService encryptionService;
    private JWTService jwtService;
    private ZadaniaUserDao zadaniaUserDao;
    private ZadaniaDAO zadaniaDAO;


    public UserService(LocalUserDao localUserDao, EncryptionService encryptionService, JWTService jwtService, ZadaniaUserDao zadaniaUserDao, ZadaniaDAO zadaniaDAO) {
        this.localUserDao = localUserDao;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
        this.zadaniaUserDao = zadaniaUserDao;
        this.zadaniaDAO = zadaniaDAO;
    }

    public void registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {
        if(localUserDao.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() ||
            localUserDao.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()){
            throw new UserAlreadyExistsException();
        };
        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        List<ZadaniaUser> zadanias = zadaniaUserDao.findAll();
        for(ZadaniaUser zadania1:zadanias){
            user.setZadaniaUsers(zadanias);
        }
        localUserDao.save(user);

    }
    public String loginUser(LoginBody loginBody){
        Optional<LocalUser> opUser = localUserDao.findByUsernameIgnoreCase(loginBody.getUsername());
        if(opUser.isPresent()){
            LocalUser user = opUser.get();
            if(encryptionService.verifyPassword(loginBody.getPassword(),user.getPassword())){
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
