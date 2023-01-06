package com.shop.model.repository;

import com.shop.model.Product;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends ListCrudRepository<Product,Long> {
}
