package com.shop.model.repository;

import com.shop.model.LocalUser;
import com.shop.model.WebOrder;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface WebOrderDao extends ListCrudRepository<WebOrder,Long> {
    List<WebOrder> findByUser(LocalUser user);


}
