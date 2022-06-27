package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.AddProductRequestDto;
import io.iamofoe.ecommerceservice.dto.response.ProductListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateProductRequestDto;

public interface ProductService {
    ProductResponseDto getProduct(long productId, long userId);
    ProductListResponseDto getProducts(long userId);
    ProductListResponseDto searchProduct(String product);
    ProductListResponseDto getProductsByACategory(long categoryId);
    ProductResponseDto addProduct(AddProductRequestDto dto);
    ProductResponseDto updateProduct(UpdateProductRequestDto dto,long productId);
}
