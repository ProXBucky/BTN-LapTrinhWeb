package com.nhommot.thitracnghiem.service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nhommot.thitracnghiem.models.Product;
import com.nhommot.thitracnghiem.models.ProductDto;
import com.nhommot.thitracnghiem.repository.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void saveProduct(Product product, ProductDto productDto) throws Exception {
        MultipartFile image = productDto.getImageFile();
        if (image.isEmpty()) {
            throw new Exception("The file is null");
        }

        Date createdAt = new Date();
        String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception ex) {
            throw new Exception("Error while saving file", ex);
        }

        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);
        repo.save(product);
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void updateProduct(Product product, ProductDto productDto) throws Exception {
        MultipartFile image = productDto.getImageFile();
        if (!image.isEmpty()) {
            String uploadDir = "public/images/";
            Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
            try {
                Files.delete(oldImagePath);
            } catch (Exception ex) {
                throw new Exception("Error while deleting old image", ex);
            }

            Date createdAt = new Date();
            String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception ex) {
                throw new Exception("Error while saving new image", ex);
            }
            product.setImageFileName(storageFileName);
        }

        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        
        repo.save(product);
    }

    public void deleteProduct(int id) {
        Product product = getProductById(id);
        if (product != null) {
            String uploadDir = "public/images/";
            Path imagePath = Paths.get(uploadDir + product.getImageFileName());
            try {
                Files.delete(imagePath);
            } catch (Exception ex) {
                System.out.print("Exception: " + ex.getMessage());
            }
            repo.delete(product);
        }
    }
}
