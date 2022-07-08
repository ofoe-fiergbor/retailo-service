package io.iamofoe.ecommerceservice.converter;
import io.iamofoe.ecommerceservice.domain.model.CheckoutTransaction;
import io.iamofoe.ecommerceservice.dto.response.CheckoutTransactionHistoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutHistoryToHistoryDtoConverter implements Converter<CheckoutTransaction, CheckoutTransactionHistoryDto>{
    private final CheckoutProductToCheckoutProductDto toCheckoutProductDto;
    @Override
    public CheckoutTransactionHistoryDto convert(CheckoutTransaction transaction) {
        return CheckoutTransactionHistoryDto.builder()
                .id(transaction.getId())
                .date(transaction.getCreated())
                .amount(transaction.getAmount())
                .products(transaction.getProducts().stream().map(toCheckoutProductDto::convert).toList())
                .build();
    }
}
