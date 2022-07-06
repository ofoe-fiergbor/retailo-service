package io.iamofoe.ecommerceservice.dto.request;

import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import lombok.*;

@Data
@NoArgsConstructor
public class UpdateProductRequestDto {
    private long userId;
    private long categoryId;
    private double price;
    private String imageUrl;
    private double quantity;
    private String description;
    private ProductVisibility status;
}
