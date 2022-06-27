package io.iamofoe.ecommerceservice.converter;

import io.iamofoe.ecommerceservice.domain.model.SellerRequest;
import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.dto.response.SellerRequestDto;
import io.iamofoe.ecommerceservice.service.UserRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
@RequiredArgsConstructor
public class SellerRequestDtoConverter implements Converter<SellerRequest, SellerRequestDto>{
    private final UserRepositoryService userRepositoryService;
    @Override
    public SellerRequestDto convert(SellerRequest source) {
        User user = userRepositoryService.getUserWithId(source.getUserId());
        return SellerRequestDto.builder()
                .userId(source.getUserId())
                .id(source.getId())
                .createDate(source.getCreateDate())
                .comment(source.getComment())
                .status(source.getStatus())
                .pictureUrl(user.getPictureUrl())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .address(user.getAddress())
                .build();
    }


}
