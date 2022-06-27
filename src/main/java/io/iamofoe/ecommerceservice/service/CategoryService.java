package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.CategoryRequestDto;
import io.iamofoe.ecommerceservice.dto.request.CategoryUpdateRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto createNewCategory(CategoryRequestDto dto);
    CategoryResponseDto updateExistingCategory(CategoryRequestDto dto, long id);
    CategoryResponseDto findCategoryById(long id);
    CategoryListResponseDto getAllCategories();
}
