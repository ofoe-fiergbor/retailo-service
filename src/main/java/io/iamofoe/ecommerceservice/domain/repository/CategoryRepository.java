package io.iamofoe.ecommerceservice.domain.repository;

import io.iamofoe.ecommerceservice.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
