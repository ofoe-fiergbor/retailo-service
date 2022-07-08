package io.iamofoe.ecommerceservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CheckoutRequestDto {
    long userId;
    List<CheckoutProductRequestDto> products;
}
