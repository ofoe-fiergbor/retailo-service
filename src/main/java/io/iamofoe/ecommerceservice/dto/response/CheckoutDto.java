package io.iamofoe.ecommerceservice.dto.response;

import io.iamofoe.ecommerceservice.domain.model.CheckoutProduct;
import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class CheckoutDto {
    List<CheckoutProduct> checkoutProducts;
    CheckoutTransaction checkoutTransaction;
}
