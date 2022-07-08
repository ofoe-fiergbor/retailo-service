package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.CheckoutRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutTransactionHistoryListDto;

public interface CheckoutTransactionService {
    CheckoutResponseDto checkoutFromShoppingCart(CheckoutRequestDto dto);
    CheckoutTransactionHistoryListDto getCheckoutTransactionHistoryForUser(long userId);


}
