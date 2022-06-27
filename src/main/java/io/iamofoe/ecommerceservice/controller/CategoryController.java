package io.iamofoe.ecommerceservice.controller;

import io.iamofoe.ecommerceservice.dto.request.CategoryRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryListResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CategoryResponseDto;
import io.iamofoe.ecommerceservice.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
@Tag(name = "Category Controller - V1")
@SecurityRequirement(name = "bearerAuth")
@EnableMethodSecurity(securedEnabled = true)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories")
    public ResponseEntity<CategoryListResponseDto> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get category by id.")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable long id) {
        return new ResponseEntity<>(categoryService.findCategoryById(id), HttpStatus.OK);
    }
    @Secured("ADMIN")
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing category")
    public ResponseEntity<CategoryResponseDto> updateExistingCategory(@RequestBody CategoryRequestDto dto, @PathVariable long id) {
        return new ResponseEntity<>(categoryService.updateExistingCategory(dto, id), HttpStatus.OK);
    }

    @Secured("ADMIN")
    @PostMapping
    @Operation(summary = "Create new a category")
    public  ResponseEntity<CategoryResponseDto> createNewCategory(@RequestBody CategoryRequestDto dto){
        return new ResponseEntity<>(categoryService.createNewCategory(dto), HttpStatus.CREATED);
    }
}
