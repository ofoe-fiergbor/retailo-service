package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.dto.request.AddProductRequestDto;
import io.iamofoe.ecommerceservice.dto.response.ProductListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateProductRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    ProductResponseDto getProduct(long productId, long userId);
    ProductListResponseDto getProducts(long userId);
    Page<ProductResponseDto> getAllProductsPaginated(Pageable page);
    ProductListResponseDto searchProduct(String product);
    ProductListResponseDto getProductsByACategory(long categoryId);
    ProductResponseDto addProduct(AddProductRequestDto dto);
    ProductResponseDto updateProduct(UpdateProductRequestDto dto,long productId);
    List<Product> getProductsByUser(long userId);
}
