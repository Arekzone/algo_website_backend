package com.shop.service;

import com.shop.model.Product;
import com.shop.model.repository.ProductDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductDAO productDAO;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    public List<Product> getProducts(){
        return productDAO.findAll();
    }
}
