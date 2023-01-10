package com.shop.api.controller.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.api.model.LoginBody;
import com.shop.api.model.LoginResponse;
import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.model.LocalUser;
import com.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
@Slf4j
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/register", consumes = "application/json")
   public ResponseEntity registerUser(@Valid @RequestBody RegistrationBody registrationBody){
    try {

        userService.registerUser(registrationBody);
        return ResponseEntity.ok().build();
    }catch (UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody){
        String jwt = userService.loginUser(loginBody);
        if(jwt==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            String.valueOf(response);
            response.getJwt();
            return ResponseEntity.ok(response);
        }
    }


    @GetMapping("/me")
    public LocalUser getLoggedUserProfile(@AuthenticationPrincipal LocalUser user){
        return user;
    }
}
