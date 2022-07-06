package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.SellerRequestDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.ProductVisibility;
import io.iamofoe.ecommerceservice.domain.model.RequestStatus;
import io.iamofoe.ecommerceservice.domain.model.SellerRequest;
import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.domain.repository.SellerRequestRepository;
import io.iamofoe.ecommerceservice.domain.repository.UserRepository;
import io.iamofoe.ecommerceservice.dto.request.CreateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.response.AllSellerRequestsDto;
import io.iamofoe.ecommerceservice.dto.response.SellerRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static io.iamofoe.ecommerceservice.domain.model.Role.SELLER;
import static io.iamofoe.ecommerceservice.domain.model.Role.USER;

@Service
@RequiredArgsConstructor
public class SellerRequestRepositoryService implements SellerRequestService {
    private final SellerRequestRepository sellerRequestRepository;
    private final SellerRequestDtoConverter sellerRequestDtoConverter;
    private final UserRepositoryService userRepositoryService;
    private final UserRepository userRepository;
    private final ProductService productService;

    @Override
    public AllSellerRequestsDto getAllRequests() {
        List<SellerRequest> sellerRequests = sellerRequestRepository.findAll();
        return AllSellerRequestsDto.builder()
                .requests(sellerRequests.stream()
                        .map(sellerRequestDtoConverter::convert).toList())
                .build();
    }

    @Override
    public SellerRequestDto createRequest(CreateSellerRequestDto dto) {
        if (sellerRequestRepository.existsByUserId(dto.getUserId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "There's already a request for user");
        }
        SellerRequest sellerRequest = sellerRequestRepository
                .save(new SellerRequest().setUserId(dto.getUserId()).setStatus(RequestStatus.PENDING));
        return sellerRequestDtoConverter.convert(sellerRequest);
    }

    @Override
    public void withdrawRequest(long userId) {
        if (!sellerRequestRepository.existsByUserId(userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no request for this user.");
        }
        sellerRequestRepository.deleteById(userId);
    }

    @Override
    public SellerRequestDto getRequest(long userId) {
        return sellerRequestDtoConverter.convert(getSellerRequest(userId));
    }

    @Override
    public SellerRequestDto updateRequest(UpdateSellerRequestDto dto, long userId) {
        changeRole(dto, userId);
        return sellerRequestDtoConverter.convert(
                sellerRequestRepository.save(getSellerRequest(userId)
                        .setStatus(dto.getStatus())
                        .setComment(dto.getComment()))
        );
    }

    private void changeRole(UpdateSellerRequestDto dto, long userId) {
        User user = userRepositoryService.getUserWithId(userId);
        switch (dto.getStatus()) {
            case PENDING, REVOKED -> {
                user.setRole(USER);
                changeUserProductStatus(userId);
            }
            case GRANTED -> user.setRole(SELLER);
        }
        userRepository.save(user);
    }

    private void changeUserProductStatus(long userId) {
        productService.getProductsByUser(userId)
                .forEach(product -> product.setProductVisibility(ProductVisibility.HIDDEN));
    }

    private SellerRequest getSellerRequest(long userId) {
        return sellerRequestRepository
                .findByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found."));
    }
}
