package com.shop.api.controller.auth.Materialy;


import com.shop.model.Materialy;
import com.shop.service.MaterialyService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/materialy")
public class MaterialyController {

    private final MaterialyService materialyService;


    public MaterialyController(MaterialyService materialyService) {
        this.materialyService = materialyService;

    }


    @GetMapping("/getAll")
    public List<Materialy> sayHello(){
        return materialyService.pobierzMaterialy();
    }




    }




