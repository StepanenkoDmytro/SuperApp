package com.example.OurApp.services;

import com.example.OurApp.models.Product;
import com.example.OurApp.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //логування
@RequiredArgsConstructor
public class ProductServices {
    private final ProductRepository productRepository;

    public List<Product> listProducts(String title) {
//        if (title != null) productRepository.findByTitle(title);
         return productRepository.findAll();
    }
    public void saveProduct(Product product) {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Integer id) {

        return productRepository.findById(id).orElse(null);
    }
}
