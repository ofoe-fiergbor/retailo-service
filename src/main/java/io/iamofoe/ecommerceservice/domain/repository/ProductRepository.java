package io.iamofoe.ecommerceservice.domain.repository;

import io.iamofoe.ecommerceservice.domain.model.Category;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import io.iamofoe.ecommerceservice.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByIdAndUser(long id, User user);

    List<Product> findProductsByUser(User user);

    List<Product> findProductsByCategory(Category category);

    @Query("select p from Product p where lower(p.name) like lower(concat('%', :nameToFind,'%')) ")
    List<Product> findByName(@Param("nameToFind") String name);

    @Query("select p from Product p where p.productVisibility=:status")
    Page<Product> findProductByProductVisibility(@Param("status") ProductVisibility status, Pageable pageable);
}
