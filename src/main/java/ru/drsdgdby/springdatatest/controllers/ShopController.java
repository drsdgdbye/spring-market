package ru.drsdgdby.springdatatest.controllers;

import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.services.CartService;
import ru.drsdgdby.springdatatest.services.ProductService;

@Controller
@RequestMapping(value = "/shop", method = RequestMethod.GET)
@Setter(onMethod_ = @Autowired)
public class ShopController {
    private ProductService productService;
    private CartService cartService;

    @RequestMapping("/")
    public String showShop(Model model,
                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "min", defaultValue = "0") Integer min,
                           @RequestParam(value = "max", defaultValue = "1000") Integer max,
                           @RequestParam(value = "size", defaultValue = "5") Integer size) {

        val productPage = productService
                .getAllPaginatedRangedProducts(PageRequest.of(page - 1, size), min, max);
        model.addAttribute("products", productPage);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("size", size);
        return "shop";
    }

    @PostMapping("/add")
    public String addProductToCart(@RequestParam Integer id) {
        Product product = productService.getProductById(id);
        cartService.addProduct(product);
        return "redirect:/shop/";
    }
}
