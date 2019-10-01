package ru.drsdgdby.springdatatest.utils;

import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    @Getter
    private Map<String, Integer> cart;

    @PostConstruct
    public void init() {
        cart = new LinkedHashMap<>();
    }
}
