package com.fms.Controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.entity.Client;
import com.fms.entity.Contract;
import com.fms.entity.Users;
import com.fms.repositoryServices.implementation.ClientRepositoryImplementation;
import com.fms.repositoryServices.implementation.ContractRepositoryImplementation;
import com.fms.repositoryServices.implementation.UserRepositoryImplementation;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author FMS
 * <p>Controller rest pour les points d'access des contrat
 * url localhost:8181/pilotage_contrat/api/v1/contract</p>
 *
 */
@Slf4j
@RestController
@RequestMapping(path = "/api/v1/contract")

public class ContractController {
    
    /**
     * <p>cree l'injection pour le repertoire contractRepository.</p>
     */
    private final ContractRepositoryImplementation contractRepository;
    private final ClientRepositoryImplementation clientRepository;
    private final UserRepositoryImplementation userRepository;
    @Autowired
    public ContractController(ContractRepositoryImplementation contractRepository, ClientRepositoryImplementation clientRepository, UserRepositoryImplementation userRepository) {
        this.contractRepository = contractRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }
    
    /**
     * <p>url: api/v1/contract/contracts<p>    
     * @return retourne un list des contracts
     */
    @GetMapping("/contracts")
    public List<Contract> getContracts(){
        return contractRepository.getcontracts();
    }
    
    /**
     * <p>url: api/v1/contract/contract/{id}</p>    
     * @return retourne une contract avec l'id
     */
    @GetMapping("/contract/{id}")
    public Contract getContractById(@PathVariable("id") Long id) {
        return contractRepository.getById(id);        
    }
    
    /**
     * <p>url: api/v1/contract/clientcontracts/{id}</p>    
     * @return retourne une liste des contrats avec l'id de client
     */
    @GetMapping("/clientcontracts/{id}")
    public List<Contract> getContractsByClientId(@PathVariable("id") Long id) {
        return contractRepository.findByClientId(id);        
    }
    
    /**
     * <p>url: api/v1/contract/userbycontract/{id}</p>    
     * @return retourne une liste des users avec l'id de contract.
     */
    @GetMapping("/userbycontract/{id}")
    public List<Users> getUserByContractId(@PathVariable("id") Long id) {
        return contractRepository.findUsersByContractId(id);        
    }
    
    /**
     * <p>url: api/v1/contract/newcontract</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si l'enregistremente de le contrat est passe ou pas
     */
    //Need to fix cleint(without and user without ids in url
    @PostMapping("/{userId}/newcontract")
    public ResponseEntity<Contract> saveContract(@PathVariable("userId") long id, @RequestBody @Valid Contract contract) {
      //  contract.setClient(clientRepository.get(contract))
        log.info("user id " + userRepository.findById(id).isPresent());
        Optional<Users> user = userRepository.findById(id);
        Optional<Client> optionalClient = clientRepository.findById(contract.getClient().getId());
        if(!optionalClient.isPresent() && !user.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        contract.setClient(optionalClient.get());
        contract.addOpFms(user.get());
        //Contract savedContract = contrac
        return ResponseEntity.ok(contractRepository.create(contract));
    }
    
    /**
     * <p>url: api/v1/contract/updatecontract</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si le modifications de contract est passe ou pas.
     */    
    @PutMapping("/updatecontract/{contract_id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("contract_id") long id, @RequestBody Contract contract){
        contract.setId(id);
        Optional<Client> optionalClient = clientRepository.findById(contract.getClient().getId());
        if(!optionalClient.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        contract.setClient(optionalClient.get());
        return ResponseEntity.ok(contractRepository.updateContract(contract));
    }
    
    @DeleteMapping("/deleteuser/{userId}/{contractid}")
    public ResponseEntity<HttpStatus> deleteUserFromContract(@PathVariable(value = "userId") Long userId, @PathVariable(value = "contractid") Long contractId) {
        Contract contract = contractRepository.getById(contractId);
           // .orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));
        contract.removeOpFms(userId);
        contractRepository.updateContract(contract);        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    
    /**
     * <p>url: api/v1/contract/deletecontract/{id}</p>  
     * @return retourne un reponse si le contrat avec l'id est Suprime.
     */
    @DeleteMapping("/deletecontract/{id}")
    public ResponseEntity<Boolean> deleteContract(@PathVariable("id") Long id){
//        Contract contract = contractRepository.getById(id);
//        for(Users user: contract.getOpFms()) {
//            userRepository.
//        }
//        userRepository.delete(0)
        return ResponseEntity.ok(contractRepository.delete(id));
    }

}
