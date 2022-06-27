package io.iamofoe.ecommerceservice.service;

import io.iamofoe.ecommerceservice.domain.model.User;
import io.iamofoe.ecommerceservice.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
