package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.converter.UserToLoginResponseDtoConverter;
import io.iamofoe.ecommerceservice.converter.UserToUserDetailsResponseDtoConverter;
import io.iamofoe.ecommerceservice.domain.model.Role;
import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.domain.repository.UserRepository;
import io.iamofoe.ecommerceservice.dto.request.LoginRequestDto;
import io.iamofoe.ecommerceservice.dto.request.RegisterUserDto;
import io.iamofoe.ecommerceservice.dto.request.UserDetailsUpdateDto;
import io.iamofoe.ecommerceservice.dto.response.LoginResponseDto;
import io.iamofoe.ecommerceservice.dto.response.UserDetailsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserRepositoryService implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final UserToLoginResponseDtoConverter userToLoginResponseDtoConverter;
    private final UserToUserDetailsResponseDtoConverter userToUserDetailsResponseDtoConverter;


    @Override
    public void registerUser(RegisterUserDto dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User with email already exists.");
        }
        userRepository.save(new User()
                .setEmail(dto.getEmail())
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setPassword(passwordEncoder.encode(dto.getPassword()))
                .setRole(Role.USER));
    }

    @Override
    public LoginResponseDto login(LoginRequestDto dto) {
        authenticateUser(dto);
        return userToLoginResponseDtoConverter.convert(getUserWithEmail(dto.getEmail()));
    }

    @Override
    public UserDetailsResponseDto updateUserDetails(UserDetailsUpdateDto dto, Long userId) {
        User user = userRepository.save(getUserWithId(userId)
                .setAddress(dto.getAddress())
                .setPhone(dto.getPhone())
                .setPictureUrl(dto.getPictureUrl()));
//        if (!getCurrentUser().getEmail().equals(user.getEmail())) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You cannot update details for this user");
//        }
        return userToUserDetailsResponseDtoConverter.convert(user);
    }

    @Override
    public UserDetailsResponseDto getUsersDetails(long userId) {
        return userToUserDetailsResponseDtoConverter.convert(getUserWithId(userId));
    }

    private void authenticateUser(LoginRequestDto dto) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authManager.authenticate(authenticationToken);
    }

    public User getCurrentUser() {
        return getUserWithEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User getUserWithEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User with email: " + email + " does not exist."));
    }

    public User getUserWithId(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User details with id: " + id + " does not exist."));
    }
}
