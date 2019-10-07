package ru.drsdgdby.springdatatest.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import ru.drsdgdby.springdatatest.entities.OrderItem;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private Map<Integer, OrderItem> cart;
    @Setter
    private Double totalPrice;

    @PostConstruct
    public void init() {
        cart = new LinkedHashMap<>();
    }
}
