package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CheckoutTransactionToCheckoutResponseDtoConverter implements Converter<CheckoutTransaction, CheckoutResponseDto> {
    @Override
    public CheckoutResponseDto convert(CheckoutTransaction transaction) {
        return CheckoutResponseDto.builder()
                .amount(transaction.getAmount())
                .id(transaction.getId())
                .date(transaction.getCreated())
                .amount(transaction.getAmount())
                .build();
    }
}
