package com.shop.service;

import com.shop.model.Image;
import com.shop.model.repository.ImageDao;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Service
public class ImageService {
    private ImageDao imageDao;

    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
    public Image save(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence " + fileName);
            }

            Image image = new Image();
            image.setName(fileName);
            image.setData(file.getBytes());

            return imageDao.save(image);
        } catch (Exception ex) {
            throw new Exception("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Image findById(Long id) {
        return imageDao.findById(id).orElse(null);
    }
}
