package ru.drsdgdby.springdatatest.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.drsdgdby.springdatatest.entities.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Double>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);
    Optional<Product> findById(Integer id);
    Optional<Product> deleteById(Integer id);
}
