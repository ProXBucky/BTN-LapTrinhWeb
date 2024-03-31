package com.nhommot.thitracnghiem.controllers;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;

import com.nhommot.thitracnghiem.models.Product;
import com.nhommot.thitracnghiem.models.ProductDto;
import com.nhommot.thitracnghiem.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return "products/CreateProduct";
        }
        
        try {
            productService.saveProduct(new Product(), productDto);
        } catch (Exception ex) {
            result.addError(new FieldError("productDto", "imageFile", ex.getMessage()));
            return "products/CreateProduct";
        }

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return "redirect:/products";
        }

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setBrand(product.getBrand());
        productDto.setCategory(product.getCategory());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());

        model.addAttribute("product", product);
        model.addAttribute("productDto", productDto);

        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
        Model model,
        @RequestParam int id,
        @Valid @ModelAttribute ProductDto productDto,
        BindingResult result
    ) {
        if (result.hasErrors()) {
            return "products/EditProduct";
        }

        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                return "redirect:/products";
            }
            productService.updateProduct(product, productDto);
        } catch (Exception ex) {
            result.addError(new FieldError("productDto", "imageFile", ex.getMessage()));
            return "products/EditProduct";
        }

        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam int id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }
}
