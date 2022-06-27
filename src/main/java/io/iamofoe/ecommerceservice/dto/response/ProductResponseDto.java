package io.iamofoe.ecommerceservice.dto.response;

import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import lombok.*;

@Value
@Builder
public class ProductResponseDto {
    long id;
    String name;
    double price;
    String imageUrl;
    double quantity;
    String description;
    ProductVisibility status;
    CategoryResponseDto category;
}
