package com.shop.model.repository;

import com.shop.model.LocalUser;
import com.shop.model.Zadania;
import com.shop.model.ZadaniaUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ZadaniaUserDao extends ListCrudRepository<ZadaniaUser,Long> {





}
