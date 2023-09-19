package com.fms.repositoryServices;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fms.entity.Contract;
import com.fms.entity.Users;
/**
 * 
 * @author FMS
 * <p>Contract repository interface qui extends le JpaRepository<p>.
 *
 */
public interface ContractRepository extends JpaRepository<Contract, Long> {
    /**
     * 
     * @param clientId id de client
     * @return les contrat pour un client
     */
    List<Contract> findByClientId(Long clientId);
    
    /**
     * 
     * @param contractId l'id de contrat
     * @return les utilisateurs qui son dans un contrat
     */
    List<Users> findOpFmsById(Long contractId);

}
