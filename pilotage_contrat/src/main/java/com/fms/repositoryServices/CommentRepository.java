package com.fms.repositoryServices;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.entity.Comment;

import jakarta.transaction.Transactional;
/**
 * 
 * @author FMS
 * <p>Comment repository interface qui extends le JpaRepository<p>.
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    /**
     * 
     * @param userId l'id user
     * @return list des users avec cette id(normalemment 1 user.
     */
    List<Comment> findByActionId(Long userId);
    
    /**
     * 
     * @param contractId id de contract
     * @return retourne liste des comments avec l'id de contrat donnees
     */
    List<Comment> findByContractId(Long contractId);
    
    /**
     * 
     * @param userId id d'utilisateur
     * <p>cette methode suprime les comments liee a l'utilisateur
     */
    @Transactional//transaction manager
    void deleteByActionId(long userId);

}
