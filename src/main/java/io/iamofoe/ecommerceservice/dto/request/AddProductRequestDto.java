package io.iamofoe.ecommerceservice.dto.request;

import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddProductRequestDto {
    private long userId;
    private long categoryId;
    private String name;
    private double price;
    private String imageUrl;
    private double quantity;
    private String description;
    private ProductVisibility status;
}
