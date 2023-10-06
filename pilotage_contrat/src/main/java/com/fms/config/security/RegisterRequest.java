package com.fms.config.security;

import com.fms.enums.entity_enums.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    
    private String prenom;
    
    private String nom;
    
    private String email;
    
    private String password;
    
    private UserRoleEnum role;

}
