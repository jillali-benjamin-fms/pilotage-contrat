package com.fms.config.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fms.entity.Users;
import com.fms.enums.entity_enums.UserRoleEnum;
import com.fms.repositoryServices.implementation.UserRepositoryImplementation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepositoryImplementation userRepository;
    private final PasswordEncoder passwordEncodeer;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    public AuthenticationResponse register(RegisterRequest request) {
        
        var user = Users.builder()
                    .prenom(request.getPrenom())
                    .nom(request.getNom())
                    .email(request.getEmail())
                    .password(passwordEncodeer.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();
        userRepository.create(user);
        var jwtToken = jwtService.generateTokenDetails(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateTokenDetails(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

}
