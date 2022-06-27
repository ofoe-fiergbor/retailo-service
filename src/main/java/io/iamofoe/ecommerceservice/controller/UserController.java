package io.iamofoe.ecommerceservice.controller;

import io.iamofoe.ecommerceservice.dto.request.LoginRequestDto;
import io.iamofoe.ecommerceservice.dto.request.RegisterUserDto;
import io.iamofoe.ecommerceservice.dto.request.UserDetailsUpdateDto;
import io.iamofoe.ecommerceservice.dto.response.LoginResponseDto;
import io.iamofoe.ecommerceservice.dto.response.UserDetailsResponseDto;
import io.iamofoe.ecommerceservice.service.UserService;
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
@RequestMapping("api/v1/user")
@Tag(name = "User Controller - V1")
@SecurityRequirement(name = "bearerAuth")
@EnableMethodSecurity(securedEnabled = true)
public class UserController {

    private final UserService userService;

    @PutMapping("/{userId}")
    @Operation(summary = "Update user's details.")
    public ResponseEntity<UserDetailsResponseDto> updateUserDetails(@RequestBody UserDetailsUpdateDto dto, @PathVariable Long userId) {
        return new ResponseEntity<>(userService.updateUserDetails(dto, userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user's profile")
    public ResponseEntity<UserDetailsResponseDto> getUserDetails(@PathVariable long userId) {
        return new ResponseEntity<>(userService.getUsersDetails(userId), HttpStatus.OK);
    }
    @PostMapping("/register")
    @Operation(summary = "Register new user.")
    public ResponseEntity<Void> registerNewUser(@RequestBody RegisterUserDto dto) {
        userService.registerUser(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "Login existing user.")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto dto) {
        return new ResponseEntity<>(userService.login(dto), HttpStatus.OK);
    }

}
