package io.iamofoe.ecommerceservice.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CategoryUpdateRequestDto {
    long id;
    String name;
}
