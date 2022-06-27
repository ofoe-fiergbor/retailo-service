package io.iamofoe.ecommerceservice.domain.repository;

import io.iamofoe.ecommerceservice.domain.model.SellerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRequestRepository extends JpaRepository<SellerRequest, Long> {
    boolean existsByUserId(long userId);
    Optional<SellerRequest> findByUserId(long userId);
}
