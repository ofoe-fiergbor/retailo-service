package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.CategoryToCategoryResponseDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.Category;
import io.iamofoe.ecommerceservice.domain.repository.CategoryRepository;
import io.iamofoe.ecommerceservice.dto.request.CategoryRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CategoryRepositoryService implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryToCategoryResponseDtoConverter categoryConverter;

    @Override
    public CategoryResponseDto createNewCategory(CategoryRequestDto dto) {
        if (categoryRepository.existsByName(dto.getName().toLowerCase())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category already exist");
        }
        return categoryConverter.convert(categoryRepository.save(new Category()
                .setName(dto.getName().toLowerCase())));
    }

    @Override
    public CategoryResponseDto updateExistingCategory(CategoryRequestDto dto, long id) {
        return categoryConverter.convert(categoryRepository.save(getCategoryById(id).setName(dto.getName().toLowerCase())));
    }

    @Override
    public CategoryResponseDto findCategoryById(long id) {
        return categoryConverter.convert(getCategoryById(id));
    }

    @Override
    public CategoryListResponseDto getAllCategories() {
        return CategoryListResponseDto.builder()
                .categories(categoryRepository.findAll()
                        .stream()
                        .map(categoryConverter::convert).toList())
                .build();
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("There's not category with id %s", id)));
    }
}
