package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutTransactionToCheckoutResponseDto implements Converter<CheckoutTransaction, CheckoutResponseDto> {
    private final CheckoutProductToCheckoutProductResponseDto checkoutProductResponseDto;

    @Override
    public CheckoutResponseDto convert(CheckoutTransaction dto) {
        return CheckoutResponseDto.builder()
                .id(dto.getId())
                .amount(dto.getAmount())
                .products(dto.getProducts().stream().map(checkoutProductResponseDto::convert).toList())
                .build();
    }
}
