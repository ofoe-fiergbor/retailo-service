package io.iamofoe.ecommerceservice.controller;

import io.iamofoe.ecommerceservice.dto.request.CreateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.request.UpdateSellerRequestDto;
import io.iamofoe.ecommerceservice.dto.response.AllSellerRequestsDto;
import io.iamofoe.ecommerceservice.dto.response.SellerRequestDto;
import io.iamofoe.ecommerceservice.service.SellerRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/seller")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Seller Request Controller - V1")
@EnableMethodSecurity(securedEnabled = true)
public class SellerRequestController {
    private final SellerRequestService sellerRequestService;

    @Secured("ADMIN")
    @GetMapping
    @Operation(summary = "Fetch all seller requests.")
    public AllSellerRequestsDto allRequests() {
        return sellerRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific seller request.")
    public ResponseEntity<SellerRequestDto> getRequest(@PathVariable("id") long userId) {
        return new ResponseEntity<>(sellerRequestService.getRequest(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Withdraw seller request.")
    public ResponseEntity<Void> withdrawRequest(@PathVariable("id") long userId) {
        sellerRequestService.withdrawRequest(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    @Operation(summary = "Update seller request.")
    public ResponseEntity<SellerRequestDto> updateRequest(@RequestBody UpdateSellerRequestDto dto, @PathVariable("id") long userId) {
        return new ResponseEntity<>(sellerRequestService.updateRequest(dto, userId), HttpStatus.OK);
    }

    @PostMapping("/request")
    @Operation(summary = "Create seller request.")
    public ResponseEntity<SellerRequestDto> createRequest(@RequestBody CreateSellerRequestDto dto) {
        return new ResponseEntity<>(sellerRequestService.createRequest(dto), HttpStatus.CREATED);
    }
}
