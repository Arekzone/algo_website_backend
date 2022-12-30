package com.shop.service;

import com.shop.api.model.LoginBody;
import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.model.LocalUser;
import com.shop.model.repository.LocalUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserDao localUserDao;
    private EncryptionService encryptionService;
    private JWTService jwtService;

    public UserService(LocalUserDao localUserDao, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDao = localUserDao;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
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
