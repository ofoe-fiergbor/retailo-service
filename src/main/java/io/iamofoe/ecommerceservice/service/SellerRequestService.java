package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.dto.request.CreateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.response.AllSellerRequestsDto;
import io.iamofoe.ecommerceservice.dto.response.SellerRequestDto;

public interface SellerRequestService {
    AllSellerRequestsDto getAllRequests();
    SellerRequestDto createRequest(CreateSellerRequestDto dto);
    void withdrawRequest(long userId);
    SellerRequestDto getRequest(long userId);
    SellerRequestDto updateRequest(UpdateSellerRequestDto dto, long userId);
}
