package io.iamofoe.ecommerceservice.controller;


import io.iamofoe.ecommerceservice.dto.request.CheckoutRequestDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutResponseDto;
import io.iamofoe.ecommerceservice.dto.response.CheckoutTransactionHistoryListDto;
import io.iamofoe.ecommerceservice.service.CheckoutTransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1/transactions")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Transaction Controller - V1")
@EnableMethodSecurity(securedEnabled = true)
public class TransactionControllers {
    private final CheckoutTransactionService checkoutTransactionService;

    @PostMapping("/checkout")
    @Operation(summary = "Checkout user's shopping cart.")
    ResponseEntity<CheckoutResponseDto> checkoutShoppingCart(@RequestBody CheckoutRequestDto dto) {
        return new ResponseEntity<>(checkoutTransactionService.checkoutFromShoppingCart(dto), HttpStatus.CREATED);
    }

    @GetMapping("/checkout/{userId}")
    @Operation(summary = "Get checkout transaction history for a user.")
    ResponseEntity<CheckoutTransactionHistoryListDto> getCheckoutHistory(@PathVariable long userId) {
        return new ResponseEntity<>(checkoutTransactionService.getCheckoutTransactionHistoryForUser(userId), HttpStatus.OK);
    }

}
