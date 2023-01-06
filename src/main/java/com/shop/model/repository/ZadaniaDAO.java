package com.shop.model.repository;

import com.shop.model.Zadania;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ZadaniaDAO extends ListCrudRepository<Zadania,Long> {
    List<Zadania> findByLocalUser_Id(Long id);

}
