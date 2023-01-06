package com.shop.model.repository;

import com.shop.model.Materialy;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialyDao extends ListCrudRepository<Materialy,Integer> {

}
