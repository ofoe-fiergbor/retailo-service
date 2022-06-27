package io.iamofoe.ecommerceservice.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AllSellerRequestsDto {
    List<SellerRequestDto> requests;
}
