package com.shop;

import com.shop.model.Image;
import com.shop.model.repository.ImageDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class WebshopSpringApplication {


    public static void main(String[] args) throws IOException {
        SpringApplication.run(WebshopSpringApplication.class, args);


    }
}
