package ru.drsdgdby.springdatatest.controllers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.drsdgdby.springdatatest.services.UserServiceImpl;
import ru.drsdgdby.springdatatest.utils.Cart;

@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping(value = "/cart", method = RequestMethod.GET)
public class CartController {
    private Cart cart;
    private UserServiceImpl userService;

    @GetMapping("/")
    public String showCart(Model model) {
        model.addAttribute("cart", cart.getCart());
        return "cart";
    }

}
