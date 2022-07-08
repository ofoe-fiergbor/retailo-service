package io.iamofoe.ecommerceservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutProductRequestDto {
    long productId;
    double price;
    double quantity;
}
