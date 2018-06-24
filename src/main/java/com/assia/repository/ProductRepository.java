package com.assia.repository;

import com.assia.domain.category.Category;
import com.assia.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, BigInteger>,JpaSpecificationExecutor<Product> {
    Optional<Product> getById(BigInteger id);
    @Query("SELECT u FROM #{#entityName} u WHERE u.category = ?1")
    List<Product> getAllCategory(Category category);
}
