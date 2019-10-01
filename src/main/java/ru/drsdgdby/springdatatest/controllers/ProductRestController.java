package ru.drsdgdby.springdatatest.controllers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.services.ProductService;

import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
@Setter(onMethod_ = {@Autowired})
public class ProductRestController {
    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product) {
        return productService.saveOrUpdateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }
}
