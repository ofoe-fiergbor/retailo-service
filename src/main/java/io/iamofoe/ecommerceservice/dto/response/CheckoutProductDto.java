package io.iamofoe.ecommerceservice.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CheckoutProductDto {
    long id;
    String name;
    double price;
    double quantity;
    String imageUrl;
    CategoryResponseDto category;
}
