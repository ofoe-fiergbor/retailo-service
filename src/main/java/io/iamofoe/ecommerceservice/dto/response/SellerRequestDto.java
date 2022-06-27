package io.iamofoe.ecommerceservice.dto.response;

import io.iamofoe.ecommerceservice.domain.model.RequestStatus;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

@Value
@Builder
public class SellerRequestDto {
    Long id;
    Long userId;
    String comment;
    Date createDate;
    Date lastUpdated;
    String pictureUrl;
    String firstName;
    String lastName;
    String email;
    String address;
    String phone;
    RequestStatus status;
}
