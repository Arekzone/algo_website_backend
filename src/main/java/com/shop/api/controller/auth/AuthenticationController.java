package com.shop.api.controller.auth;

import com.shop.api.model.LoginBody;
import com.shop.api.model.LoginResponse;
import com.shop.api.model.RegistrationBody;
import com.shop.exception.UserAlreadyExistsException;
import com.shop.service.UserService;
import jakarta.validation.Valid;
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
            return ResponseEntity.ok(response);
        }
    }

}
