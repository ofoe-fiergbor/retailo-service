package io.iamofoe.ecommerceservice.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterUserDto {
    private String email;
    private String password;
    private String lastName;
    private String firstName;
}
