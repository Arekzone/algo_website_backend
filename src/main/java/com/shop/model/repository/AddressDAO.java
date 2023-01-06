package com.shop.model.repository;

import com.shop.model.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDAO extends ListCrudRepository<Address,Long> {

    List<Address> findByLocalUser_Id(Long id);

}
