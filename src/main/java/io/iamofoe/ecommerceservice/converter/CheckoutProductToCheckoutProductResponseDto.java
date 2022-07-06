package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.CheckoutProduct;
import io.iamofoe.ecommerceservice.dto.response.CheckoutProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutProductToCheckoutProductResponseDto implements Converter<CheckoutProduct, CheckoutProductResponseDto> {

    private final CategoryToCategoryResponseDtoConverter toCategoryResponseDtoConverter;

    @Override
    public CheckoutProductResponseDto convert(CheckoutProduct dto) {
        return CheckoutProductResponseDto.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .name(dto.getProduct().getName())
                .price(dto.getProduct().getPrice())
                .productId(dto.getProduct().getId())
                .category(toCategoryResponseDtoConverter.convert(dto.getProduct().getCategory()))
                .imageUrl(dto.getProduct().getImageUrl())
                .description(dto.getProduct().getDescription())
                .build();
    }
}
