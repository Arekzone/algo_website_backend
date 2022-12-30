package com.shop.service;

import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.model.LocalUser;
import com.shop.model.repository.LocalUserDao;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private LocalUserDao localUserDao;

    public UserService(LocalUserDao localUserDao) {
        this.localUserDao = localUserDao;
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
        //TODO: Encrypt passwords!!
        user.setPassword(registrationBody.getPassword());
        localUserDao.save(user);

    }
}
