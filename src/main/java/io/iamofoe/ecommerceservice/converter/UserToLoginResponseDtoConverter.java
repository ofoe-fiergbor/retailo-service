package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.dto.response.LoginResponseDto;
import io.iamofoe.ecommerceservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToLoginResponseDtoConverter implements Converter<User, LoginResponseDto> {
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponseDto convert(User source) {
        return LoginResponseDto.builder()
                .email(source.getEmail())
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .address(checkNull(source.getAddress()))
                .phone(checkNull(source.getPhone()))
                .pictureUrl(checkNull(source.getPictureUrl()))
                .token(jwtUtil.createAccessToken(source.getEmail()))
                .role(source.getRole())
                .build();
    }

    public String checkNull(String string) {
        return string == null ? "" : string;
    }
}
