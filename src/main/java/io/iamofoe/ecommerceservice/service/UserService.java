package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.LoginRequestDto;
import io.iamofoe.ecommerceservice.dto.request.RegisterUserDto;
import io.iamofoe.ecommerceservice.dto.request.UserDetailsUpdateDto;
import io.iamofoe.ecommerceservice.dto.response.LoginResponseDto;
import io.iamofoe.ecommerceservice.dto.response.UserDetailsResponseDto;

public interface UserService {
    void registerUser(RegisterUserDto dto);
    LoginResponseDto login(LoginRequestDto dto);
    UserDetailsResponseDto updateUserDetails(UserDetailsUpdateDto dto, Long userId);
    UserDetailsResponseDto getUsersDetails(long userId);

}
