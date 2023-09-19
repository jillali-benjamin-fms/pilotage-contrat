package com.fms.entity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fms.enums.entity_enums.UserRoleEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern.Flag;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter

//@NamedQuery(name = User.FIND_ALL_USERS, query = "Select u from User orderby u.nom")
//@NamedQuery(name = User.FIND_USER_BY_ID, query = "Select u from User where u.id = :id orderby u.nom")
//@NamedQuery(name = User.FIND_USER_BY_EMAIL, query = "Select u from User where u.email = :email orderby u.nom")
//@NamedQuery(name = User.GET_USER_PASSWORD, query = "Select u.password from User where u.password = :password")
public class Users extends AbstractElementsEntity implements UserDetails{
	
	/**
	 * <p>les constantes des requêtes <p>
	 */	
//	public static final String FIND_ALL_USERS = "User.findAllUser";
//	public static final String FIND_USER_BY_ID = "User.findUserById";
//	public static final String FIND_USER_BY_EMAIL = "User.findUserByEmail";
//	public static final String GET_USER_PASSWORD = "User.GetUserMotPasse";
	
	/**
	 * <p>Le prenom d'utilisateur <p>
	 */	
	@Size(min = 2, max = 40, message = "Nom doit pas etre vide.")
	@NotNull(message = "Prénom non renseigné!")	
	private String prenom;
	
	/**
	 * <p>Le nom d'utilisateur <p>
	 */	
	@Size(min = 2, max = 40, message = "Prenom doit pas etre vide.")
	@NotNull(message = "Prénom non renseigné!")		
	private String nom;
	
	/**
	 * <p>Le Context?? <p>
	 */		
	private String abrege;
	
	@Column(unique = true)
	@Size(min = 2, max = 40, message = "Email doit pas etre vide.")
	@Email(message = "Email non renseigné!", regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Flag.CASE_INSENSITIVE)
	/**
	 * <p>L'email d'utilisateur en utilise des pattern matching pour assure qu le format d'email est bon<p>
	 */	
	private String email;
	
	/**
	 * <p>Le mot d'utilisateur enregistre hashed <p>
	 */	
	@NotNull(message = "Mot de passe non renseigné!")
	@Size(min = 8)
	private String password;
	
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {
	        CascadeType.MERGE, CascadeType.REMOVE
        },
         mappedBy = "opFms")
    @JsonBackReference
	private Collection<Contract> contracts;

	
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}



