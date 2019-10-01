package ru.drsdgdby.springdatatest.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.drsdgdby.springdatatest.entities.Product;
import ru.drsdgdby.springdatatest.repositories.ProductRepository;
import ru.drsdgdby.springdatatest.repositories.specs.ProductSpecs;

import java.util.List;

@Service
@Transactional
@Setter(onMethod_ = {@Autowired})
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> getAllPaginatedRangedProducts(Pageable pageable, int min, int max) {
        Specification<Product> specification = Specification.where(null);
        specification = specification.and(ProductSpecs.costMinAndMaxBetween(min, max));
        return productRepository.findAll(specification, pageable);
    }

    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.findById(product.getId()).map(newProduct -> {
            newProduct.setTitle(product.getTitle());
            newProduct.setCost(product.getCost());
            return productRepository.save(newProduct);
        }).orElseGet(() -> {
            product.setId(null);
            return productRepository.save(product);
        });
    }
}
