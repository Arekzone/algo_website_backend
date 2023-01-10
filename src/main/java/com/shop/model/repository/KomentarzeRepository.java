package com.shop.model.repository;

import com.shop.model.Komentarze;
import com.shop.model.Zadania;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KomentarzeRepository extends ListCrudRepository<Komentarze, Long> {
    List<Komentarze> findByZadania_Id(Long id);


}






