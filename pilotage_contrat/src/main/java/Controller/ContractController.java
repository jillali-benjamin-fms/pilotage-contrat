package Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fms.entity.Contract;
import com.fms.entity.Users;

import jakarta.validation.Valid;
import repositoryServices.implementation.ContractRepositoryImplementation;

/**
 * 
 * @author FMS
 * <p>Controller rest pour les points d'access des contrat
 * url localhost:8181/pilotage_contrat/api/v1/contract</p>
 *
 */
@RestController
@RequestMapping(path = "/api/v1/contract")

public class ContractController {
    
    /**
     * <p>cree l'injection pour le repertoire contractRepository.</p>
     */
    private final ContractRepositoryImplementation contractRepository;
    @Autowired
    public ContractController(ContractRepositoryImplementation contractRepository) {
        this.contractRepository = contractRepository;
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
    @PostMapping("/newcontract")
    public ResponseEntity<Contract> saveContract(@RequestBody @Valid Contract contract) {
        return ResponseEntity.ok(contractRepository.create(contract));
    }
    
    /**
     * <p>url: api/v1/contract/updatecontract</p>
     * <p>le @@RequestBody prendre les elements dans le cour de requette en generale un json</p>     
     * @return retourne un reponse si le modifications de contract est passe ou pas.
     */    
    @PostMapping("/updatecontract")
    public ResponseEntity<Contract> updateContract(@RequestBody Contract contract){
        return ResponseEntity.ok(contractRepository.updateContract(contract));
    }
    
    /**
     * <p>url: api/v1/contract/deletecontract/{id}</p>  
     * @return retourne un reponse si le contrat avec l'id est Suprime.
     */
    @DeleteMapping("/deletecontract/{id}")
    public ResponseEntity<Boolean> deleteContract(@PathVariable("id") Long id){
        return ResponseEntity.ok(contractRepository.delete(id));
    }

}
