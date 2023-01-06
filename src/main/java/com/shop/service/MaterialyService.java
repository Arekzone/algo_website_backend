package com.shop.service;

import com.shop.model.Materialy;
import com.shop.model.repository.MaterialyDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialyService {

    private final MaterialyDao MaterialyDao;


    public MaterialyService(MaterialyDao MaterialyDao) {
        this.MaterialyDao = MaterialyDao;
    }

    public List<Materialy> pobierzMaterialy(){
        return MaterialyDao.findAll();
    }
    public Materialy zapiszDanie(Materialy materialy){
        MaterialyDao.save(materialy);
        return materialy;
    }
    public Optional<Materialy> findById(int id){
        return MaterialyDao.findById(id);
    }



}
