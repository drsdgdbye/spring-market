package ru.drsdgdby.springdatatest.repositories.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import ru.drsdgdby.springdatatest.entities.Product;

@Component
public class ProductSpecs {
    public static Specification<Product> costMinAndMaxBetween(int min, int max) {
        return (Specification<Product>) (r, cq, cb) -> cb.between(r.get("cost"), min, max);
    }
}
