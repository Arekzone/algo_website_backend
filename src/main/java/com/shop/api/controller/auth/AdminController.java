package com.shop.api.controller.auth;

import com.shop.api.controller.auth.Materialy.MaterialyBody;
import com.shop.api.model.ZadanieBody;
import com.shop.model.repository.ZadaniaDAO;
import com.shop.service.MaterialyService;
import com.shop.service.ZadaniaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    private final ZadaniaDAO zadaniaDAO;
    private final ZadaniaService zadaniaService;
    private final MaterialyService materialyService;

    public AdminController(ZadaniaDAO zadaniaDAO, ZadaniaService zadaniaService, MaterialyService materialyService) {
        this.zadaniaDAO = zadaniaDAO;
        this.zadaniaService = zadaniaService;
        this.materialyService = materialyService;
    }

    @PostMapping("/form")
    public ResponseEntity handleFormData(@RequestBody ZadanieBody zadanieBody) {
        zadaniaService.handleForm(zadanieBody);
        return ResponseEntity.ok().build();
    }
//    @PostMapping("/form/materialy")
//    public ResponseEntity handleFormMaterialy(@RequestBody MaterialyBody materialyBody){
//        materialyService.handleForm(MaterialyBody);
//        return ResponseEntity.ok().build();
//    }
}
