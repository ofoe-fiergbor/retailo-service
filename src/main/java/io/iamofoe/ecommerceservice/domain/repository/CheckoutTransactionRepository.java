package io.iamofoe.ecommerceservice.domain.repository;

import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckoutTransactionRepository extends JpaRepository<CheckoutTransaction, Long> {
    List<CheckoutTransaction> findCheckoutTransactionsByUser(User user);
}
