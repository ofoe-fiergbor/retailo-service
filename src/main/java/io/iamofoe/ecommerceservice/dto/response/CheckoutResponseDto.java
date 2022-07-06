package io.iamofoe.ecommerceservice.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class CheckoutResponseDto {
    long id;
    double amount;
    Date createdDate;
    List<CheckoutProductResponseDto> products;
}
