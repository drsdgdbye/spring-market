package ru.drsdgdby.springdatatest.controllers;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.services.ProductService;

import javax.annotation.PostConstruct;

//TODO подумать, как избавиться от дефолтных значений в @requestparam

@Controller
@RequestMapping(value = "/products", method = RequestMethod.GET)
public class ProductController {
    @Getter
    private Product addProduct;
    @Setter(onMethod_ = @Autowired)
    private ProductService productService;

    @PostConstruct
    public void init() {
        addProduct = new Product();
    }

    @GetMapping("/")
    public String showAllRangedProducts(Model model,
                                        @RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "min", defaultValue = "0") int min,
                                        @RequestParam(value = "max", defaultValue = "1000") int max,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        val productPage = productService
                .getAllPaginatedRangedProducts(PageRequest.of(page - 1, size), min, max);
        model.addAttribute("products", productPage);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("size", size);
        model.addAttribute("addProduct", addProduct);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(name = "addProduct") Product product) {
        productService.saveOrUpdateProduct(product);
        return "redirect:/products/";
    }
}
