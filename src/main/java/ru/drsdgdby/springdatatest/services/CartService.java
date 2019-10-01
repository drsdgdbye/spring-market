package ru.drsdgdby.springdatatest.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.utils.Cart;

@Service
@Transactional
@Setter(onMethod_ = {@Autowired})
public class CartService {
    private Cart cart;

    public void addProduct(Product product) {
        cart.getCart().merge(product.getTitle(), 1, Integer::sum);
    }
}
