package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductToProductResponseDtoConverter implements Converter<Product, ProductResponseDto> {
    private final CategoryToCategoryResponseDtoConverter categoryConverter;

    @Override
    public ProductResponseDto convert(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .status(product.getProductVisibility())
                .category(categoryConverter.convert(product.getCategory()))
                .build();
    }

}
