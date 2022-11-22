package com.example.OurApp.controllers;

import com.example.OurApp.models.Product;
import com.example.OurApp.services.ProductServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //контролює наше звернення (як користувача) до застосунку (http запроси)
@RequiredArgsConstructor
    /*
    Цією аннтоацією я замінив ось цей код:
    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }
     */
public class ProductController {
    private final ProductServices productServices; //хороший тон прописати final


    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productServices.listProducts(title));
        return "products"; //назва представлення в тілі документу (має через command+CLICK переходить на ресурс)
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productServices.getProductById(id));
        return "product-info";
    }
    @PostMapping("/product/create")
    public String createProduct(Product product) {
        productServices.saveProduct(product);
        return "redirect:/";
    }


    @PostMapping("/product/delete/{id}")
    String deleteProduct(@PathVariable Integer id) {
        productServices.deleteProduct(id);
        return "redirect:/";
    }
}
