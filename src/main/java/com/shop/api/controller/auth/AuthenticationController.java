package com.shop.api.controller.auth;

import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
   public ResponseEntity registerUser(@RequestBody RegistrationBody registrationBody){
    try {
        userService.registerUser(registrationBody);
        return ResponseEntity.ok().build();
    }catch (UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    }

}
