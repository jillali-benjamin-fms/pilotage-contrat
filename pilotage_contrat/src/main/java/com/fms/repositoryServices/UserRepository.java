package com.fms.repositoryServices;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.Users;

import jakarta.transaction.Transactional;

/**
 * 
 * @author FMS
 * <p>Users repository interface qui extends le JpaRepository<p>.
 *
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<Users, Long> {
    
    /**
     * 
     * @param password mot de pass d'utilisateur
     * @return le mot de l'utlisatuer
     */
    //Users findPassword(String password);
    boolean existsByPassword(String password);
    /**
     * 
     * @param email email d'utlisatuer
     * @return liste des users qui a cette email.
     *Optional besoin pas des users.
     */
    Optional<Users> findByEmail(String email);

}
