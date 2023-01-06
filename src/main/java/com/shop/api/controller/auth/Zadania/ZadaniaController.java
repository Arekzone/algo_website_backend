package com.shop.api.controller.auth.Zadania;

import com.shop.model.LocalUser;
import com.shop.model.Zadania;
import com.shop.model.repository.ZadaniaDAO;
import com.shop.service.ZadaniaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/zadania")
@CrossOrigin("*")
public class ZadaniaController {
    private final ZadaniaService zadaniaService;
    private final ZadaniaDAO zadaniaDAO;

    public ZadaniaController(ZadaniaService zadaniaService, ZadaniaDAO zadaniaDAO) {
        this.zadaniaService = zadaniaService;
        this.zadaniaDAO = zadaniaDAO;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Zadania>> getZadania(){
        List<Zadania> zadania= zadaniaDAO.findAll();
        return ResponseEntity.ok(zadania);
    }
//    @GetMapping("/zadania/uzytkownika")
//    public ResponseEntity<List<Zadania>> getZadania(@AuthenticationPrincipal LocalUser localUser){
//        return ResponseEntity.ok();
//    }
}
