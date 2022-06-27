package io.iamofoe.ecommerceservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequestDto {
    private String email;
    private String password;
}
