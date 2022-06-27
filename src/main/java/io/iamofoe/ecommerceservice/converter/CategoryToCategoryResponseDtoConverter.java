package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.Category;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.dto.response.CategoryResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryResponseDtoConverter implements Converter<Category, CategoryResponseDto> {

    @Override
    public CategoryResponseDto convert(Category category) {
        return CategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
