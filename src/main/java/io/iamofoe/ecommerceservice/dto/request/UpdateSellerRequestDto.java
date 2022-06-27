package io.iamofoe.ecommerceservice.dto.request;

import io.iamofoe.ecommerceservice.domain.model.RequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateSellerRequestDto {
    private RequestStatus status;
    private String comment;
}
