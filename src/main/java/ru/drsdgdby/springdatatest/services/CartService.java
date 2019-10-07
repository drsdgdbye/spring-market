package ru.drsdgdby.springdatatest.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.drsdgdby.springdatatest.entities.OrderItem;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.utils.Cart;

import java.util.Map;

@Service
@Transactional
@Setter(onMethod_ = {@Autowired})
@Getter
public class CartService {
    private Cart cart;

    public void addProduct(Product product) {
        OrderItem orderItem = cart.getCart().get(product.getId());
        if (orderItem == null) {
            orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setPrice(product.getCost());
            orderItem.setAmount(0);
        }
        orderItem.setAmount(orderItem.getAmount() + 1);
        orderItem.setTotalPrice(orderItem.getPrice() * orderItem.getAmount());
        cart.getCart().put(product.getId(), orderItem);
    }

    public void removeItem(Integer id) {
        OrderItem orderItem = cart.getCart().get(id);
        if (orderItem.getAmount() > 1) {
            orderItem.setAmount(orderItem.getAmount() - 1);
            recalculate();
        }
        cart.getCart().remove(id);
    }

    public Map<Integer, OrderItem> items() {
        return cart.getCart();
    }

    private void recalculate() {
        cart.getCart().values().forEach(x -> cart.setTotalPrice(x.getTotalPrice()));
    }
}
