package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.CheckoutRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CheckoutService {
    CheckoutResponseDto createCheckoutTransaction(CheckoutRequestDto dto);
    Page<CheckoutResponseDto> checkoutTransactionHistory(Pageable pageable, long userId);
}
