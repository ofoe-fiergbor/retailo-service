package io.iamofoe.ecommerceservice.dto.response;

import io.iamofoe.ecommerceservice.domain.model.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDetailsResponseDto {
    Long id;
    String email;
    String firstName;
    String lastName;
    String address;
    String phone;
    String pictureUrl;
    Role role;
}
