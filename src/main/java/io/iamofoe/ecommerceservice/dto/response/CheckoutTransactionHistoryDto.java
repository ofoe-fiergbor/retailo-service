package io.iamofoe.ecommerceservice.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class CheckoutTransactionHistoryDto {
    long id;
    Date date;
    double amount;
    List<CheckoutProductDto> products;
}
