package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.CheckoutProduct;
import io.iamofoe.ecommerceservice.dto.response.CategoryResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutProductDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CheckoutProductToCheckoutProductDto implements Converter<CheckoutProduct, CheckoutProductDto> {

    @Override
    public CheckoutProductDto convert(CheckoutProduct product) {
        return CheckoutProductDto.builder()
                .id(product.getId())
                .name(product.getProduct().getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .imageUrl(product.getProduct().getImageUrl())
                .category(CategoryResponseDto.builder()
                        .id(product.getProduct().getCategory().getId())
                        .name(product.getProduct().getCategory().getName())
                        .build())
                .build();
    }
}
