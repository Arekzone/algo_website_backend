package com.shop.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface UserZadanieRepository extends ListCrudRepository<UserZadanie, Long> {

    @Query(value = "SELECT uz.task.id as zadanie_id, COUNT(uz.wynik_uzytkownika) as wynik_count FROM UserZadanie uz GROUP BY uz.task.id ORDER BY wynik_count DESC LIMIT 12")
    List<Object[]> find12MostDoneTasks();

}