package io.iamofoe.ecommerceservice.dto.request;

import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class CheckoutRequestDto {
    List<ProductResponseDto> products;
    long userId;
}
