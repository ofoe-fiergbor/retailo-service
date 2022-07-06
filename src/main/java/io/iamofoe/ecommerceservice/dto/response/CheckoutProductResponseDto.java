package io.iamofoe.ecommerceservice.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckoutProductResponseDto {
    long id;
    String name;
    double price;
    long productId;
    double quantity;
    CategoryResponseDto category;
    String imageUrl;
    String description;
}
