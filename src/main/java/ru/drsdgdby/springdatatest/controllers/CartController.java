package ru.drsdgdby.springdatatest.controllers;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.drsdgdby.springdatatest.services.CartService;

import javax.servlet.http.HttpServletRequest;

//TODO реализовать оформление заказа для гостя. создать минимально возможную учетную запись

@Controller
@Setter(onMethod_ = {@Autowired})
@RequestMapping(value = "/cart", method = RequestMethod.GET)
public class CartController {
    private CartService cartService;

    @GetMapping("/")
    public String showCart(Model model) {
        model.addAttribute("items", cartService.items().values());
        return "cart";
    }

    @PostMapping("/delete")
    public String deleteOrderItem(@RequestParam Integer id, HttpServletRequest request) {
        cartService.removeItem(id);
        return "redirect:" + request.getHeader("Referer");
    }

}
