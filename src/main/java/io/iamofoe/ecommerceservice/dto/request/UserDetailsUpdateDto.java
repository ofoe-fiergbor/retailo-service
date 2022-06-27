package io.iamofoe.ecommerceservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDetailsUpdateDto {
    private String address;
    private String phone;
    private String pictureUrl;
}
