package io.iamofoe.ecommerceservice.dto.response;

import lombok.*;


@Value
@Builder
public class CategoryResponseDto {
    long id;
    String name;
}
