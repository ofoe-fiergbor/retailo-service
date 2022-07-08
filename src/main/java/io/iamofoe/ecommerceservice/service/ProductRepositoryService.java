package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.ProductToProductResponseDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.Product;
import io.iamofoe.ecommerceservice.domain.repository.ProductRepository;
import io.iamofoe.ecommerceservice.dto.request.AddProductRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateProductRequestDto;
import io.iamofoe.ecommerceservice.dto.response.ProductListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static io.iamofoe.ecommerceservice.domain.model.ProductVisibility.AVAILABLE;
import static io.iamofoe.ecommerceservice.domain.model.ProductVisibility.HIDDEN;

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
                .products(getProductsByUser(userId)
                        .stream()
                        .map(productResponseDtoConverter::convert).toList())
                .build();
    }

    @Override
    public Page<ProductResponseDto> getAllProductsPaginated(Pageable page) {
        Page<Product> allProducts = productRepository.findProductByProductVisibility(AVAILABLE, page);
        return allProducts.map(productResponseDtoConverter::convert);
    }

    @Override
    public ProductListResponseDto searchProduct(String productName) {
        return ProductListResponseDto.builder()
                .products(productRepository
                        .findByName(productName)
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
        Product newProduct = saveProduct(new Product()
                .setName(dto.getName())
                .setPrice(dto.getPrice())
                .setQuantity(dto.getQuantity())
                .setImageUrl(dto.getImageUrl())
                .setDescription(dto.getDescription())
                .setProductVisibility(HIDDEN)
                .setUser(userRepositoryService.getUserWithId(dto.getUserId()))
                .setCategory(categoryRepositoryService.getCategoryById(dto.getCategoryId())));
        return productResponseDtoConverter.convert(newProduct);
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto dto, long productId) {
        Product updatedProduct = saveProduct(
                getProductByIdAndUser(dto, productId)
                    .setCategory(categoryRepositoryService.getCategoryById(dto.getCategoryId()))
                    .setPrice(dto.getPrice())
                    .setImageUrl(dto.getImageUrl())
                    .setQuantity(dto.getQuantity())
                    .setDescription(dto.getDescription().toLowerCase())
                    .setProductVisibility(dto.getStatus())
        );
        return productResponseDtoConverter.convert(updatedProduct);
    }

    private Product getProductByIdAndUser(UpdateProductRequestDto dto, long productId) {
        return productRepository.findProductByIdAndUser(productId, userRepositoryService.getUserWithId(dto.getUserId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with id: " + productId + " not found."));
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProductsByUser(long userId) {
        return productRepository
                .findProductsByUser(userRepositoryService.getUserWithId(userId));
    }

    public Product getProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
