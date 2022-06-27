package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.ProductToProductResponseDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import io.iamofoe.ecommerceservice.domain.repository.ProductRepository;
import io.iamofoe.ecommerceservice.dto.request.AddProductRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateProductRequestDto;
import io.iamofoe.ecommerceservice.dto.response.ProductListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRepositoryService implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepositoryService userRepositoryService;
    private final ProductToProductResponseDtoConverter productResponseDtoConverter;
    private final CategoryRepositoryService categoryRepositoryService;

    @Override
    public ProductResponseDto getProduct(long productId, long userId) {
        return productResponseDtoConverter.convert(productRepository
                .findProductByIdAndUser(productId, userRepositoryService.getUserWithId(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + productId + " not found for user with id: " + userId)));
    }

    @Override
    public ProductListResponseDto getProducts(long userId) {
        return ProductListResponseDto.builder()
                .products(productRepository
                        .findProductsByUser(userRepositoryService.getUserWithId(userId))
                        .stream()
                        .map(productResponseDtoConverter::convert).toList())
                .build();
    }

    @Override
    public ProductListResponseDto searchProduct(String productName) {
        return ProductListResponseDto.builder()
                .products(productRepository
                        .findProductsByName(productName.toLowerCase())
                        .stream().map(productResponseDtoConverter::convert)
                        .toList())
                .build();
    }

    @Override
    public ProductListResponseDto getProductsByACategory(long categoryId) {
        List<Product> products = productRepository
                .findProductsByCategory(categoryRepositoryService.getCategoryById(categoryId));
        return ProductListResponseDto.builder()
                .products(products.stream().map(productResponseDtoConverter::convert).toList())
                .build();
    }

    @Override
    public ProductResponseDto addProduct(AddProductRequestDto dto) {
        Product newProduct = productRepository.save(new Product()
                .setName(dto.getName())
                .setPrice(dto.getPrice())
                .setImageUrl(dto.getImageUrl())
                .setDescription(dto.getDescription())
                .setProductVisibility(ProductVisibility.AVAILABLE)
                .setUser(userRepositoryService.getUserWithId(dto.getUserId()))
                .setCategory(categoryRepositoryService.getCategoryById(dto.getCategoryId())));
        return productResponseDtoConverter.convert(newProduct);
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto dto, long productId) {
        Product updatedProduct = productRepository.save(productRepository.findProductByIdAndUser(productId, userRepositoryService.getUserWithId(productId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + productId + " not found."))
                .setCategory(categoryRepositoryService.getCategoryById(dto.getCategoryId()))
                .setPrice(dto.getPrice())
                .setImageUrl(dto.getImageUrl())
                .setQuantity(dto.getQuantity())
                .setDescription(dto.getDescription())
                .setProductVisibility(dto.getProductVisibility())
        );
        return productResponseDtoConverter.convert(updatedProduct);
    }
}