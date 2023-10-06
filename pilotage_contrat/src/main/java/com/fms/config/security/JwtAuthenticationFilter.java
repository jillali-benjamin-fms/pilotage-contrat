package com.fms.config.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fms.entity.Users;
import com.fms.repositoryServices.implementation.UserRepositoryImplementation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
    
    private final JwtService jwtService;
    
    @Autowired
    private UserRepositoryImplementation userRepository;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
//        if(request.getServletPath().contains("api/v1/auth")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        
        final String authHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String userEmail;
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authHeader.substring(7);
        userEmail = jwtService.extractUserEmail(jwtToken);// todo extract user email from JWT token
        
        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<Users> userDetails = this.userRepository.findByEmail(userEmail);
            //var isTokenValid = tokenRepository
            if(userDetails.isPresent() && jwtService.isTokenValid(jwtToken, userDetails.get())) {
                log.info("role " + userDetails.get().getAuthorities());
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails.get(), null, userDetails.get().getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //updates security context if user is valid
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
        
        
    }
    

}
