package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.dto.response.UserDetailsResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDetailsResponseDtoConverter implements Converter<User, UserDetailsResponseDto> {
    @Override
    public UserDetailsResponseDto convert(User source) {
        return UserDetailsResponseDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .phone(source.getPhone())
                .email(source.getEmail())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .role(source.getRole())
                .pictureUrl(source.getPictureUrl())
                .build();
    }
}
