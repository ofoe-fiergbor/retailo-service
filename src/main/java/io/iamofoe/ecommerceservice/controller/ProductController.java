package io.iamofoe.ecommerceservice.controller;

import io.iamofoe.ecommerceservice.dto.request.AddProductRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateProductRequestDto;
import io.iamofoe.ecommerceservice.dto.response.ProductListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.ProductResponseDto;
import io.iamofoe.ecommerceservice.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@Tag(name = "Product Controller - V1")
@SecurityRequirement(name = "bearerAuth")
@EnableMethodSecurity(securedEnabled = true)
public class ProductController {

    private final ProductService productService;


    @GetMapping
    @Operation(summary = "Get all products paginated")
    public Page<ProductResponseDto> getAllProducts(Pageable pageable) {
        return productService.getAllProductsPaginated(pageable);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get all products for a category and/or by name.")
    public ResponseEntity<ProductListResponseDto> getAllProductsForCategory(@PathVariable long categoryId) {
        return new ResponseEntity<>(productService.getProductsByACategory(categoryId), HttpStatus.OK);
    }
    @GetMapping("/product")
    @Operation(summary = "Search for a product.")
        public ResponseEntity<ProductListResponseDto> searchForProduct(@RequestParam String productName) {
        return new ResponseEntity<>(productService.searchProduct(productName), HttpStatus.OK);
    }

    @Secured({"SELLER", "ADMIN"})
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get details for all products for a user.")
    public ResponseEntity<ProductListResponseDto> getAllProducts(@PathVariable long userId) {
        return new ResponseEntity<>(productService.getProducts(userId), HttpStatus.OK);
    }

    @Secured({"SELLER", "ADMIN"})
    @GetMapping("/{userId}/{productId}")
    @Operation(summary = "Get details of a specific product.")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable long productId, @PathVariable long userId) {
        return new ResponseEntity<>(productService.getProduct(productId, userId), HttpStatus.OK);
    }

    @Secured("SELLER")
    @PostMapping
    @Operation(summary = "Add a new product.")
    public ResponseEntity<ProductResponseDto> addNewProduct(@RequestBody AddProductRequestDto dto) {
        return new ResponseEntity<>(productService.addProduct(dto), HttpStatus.CREATED);
    }

    @Secured("SELLER")
    @PutMapping("/{productId}")
    @Operation(summary = "Update an existing product.")
    public ResponseEntity<ProductResponseDto> updateExistingProduct(@PathVariable long productId, @RequestBody UpdateProductRequestDto dto) {
        return new ResponseEntity<>(productService.updateProduct(dto, productId), HttpStatus.CREATED);
    }
}
