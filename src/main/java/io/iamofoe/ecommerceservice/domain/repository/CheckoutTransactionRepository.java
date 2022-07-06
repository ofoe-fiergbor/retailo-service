package io.iamofoe.ecommerceservice.domain.repository;

import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import io.iamofoe.ecommerceservice.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutTransactionRepository extends JpaRepository<CheckoutTransaction, Long> {
//    @Query("select p from Product p where p.productVisibility=:status")
//    Page<Product> findProductByProductVisibility(@Param("status") ProductVisibility status, Pageable pageable);

    Page<CheckoutTransaction> findCheckoutTransactionsByUser(Pageable pageable, User user);
}
