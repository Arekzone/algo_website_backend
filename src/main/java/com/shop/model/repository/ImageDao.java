package com.shop.model.repository;

import com.shop.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageDao extends CrudRepository<Image, Long> {

}
