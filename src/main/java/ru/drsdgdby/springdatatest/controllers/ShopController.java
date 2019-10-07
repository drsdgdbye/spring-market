package ru.drsdgdby.springdatatest.controllers;

import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.services.CartService;
import ru.drsdgdby.springdatatest.services.ProductService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

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
                           @RequestParam(value = "size", required = false) @CookieValue(value = "size", required = false)
                                   Integer size, HttpServletResponse response,
                           @RequestParam Map<String, String> params) {

        if (size == null) {
            size = 5;
            response.addCookie(new Cookie("size", String.valueOf(size)));
        }

        val productPage = productService
                .getAllPaginatedRangedProducts(PageRequest.of(page - 1, size), min, max);
        model.addAttribute("products", productPage);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("size", size);
        return "shop";
    }

    @PostMapping("/add")
    public String addProductToCart(@RequestParam Integer id, HttpServletRequest request) {
        Product product = productService.getProductById(id);
        cartService.addProduct(product);
        return "redirect:" + request.getHeader("Referer");
    }
}
