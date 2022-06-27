package io.iamofoe.ecommerceservice.security;

import io.iamofoe.ecommerceservice.service.CustomUserDetailsService;
import io.iamofoe.ecommerceservice.util.JwtUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtFilter(CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String accessToken = authHeader.substring(7);
            if (accessToken.isBlank()) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No Bearer token in Authorization header");
            } else {
                try {
                    String claim = jwtUtil.getJwtClaim(accessToken);
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(claim);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token.");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
