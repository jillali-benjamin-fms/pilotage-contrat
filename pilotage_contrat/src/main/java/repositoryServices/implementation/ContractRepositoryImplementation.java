package repositoryServices.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.entity.Contract;
import com.fms.entity.Users;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import repositoryServices.ContractRepository;
import repositoryServices.UserRepository;

@Service
@Transactional
@Slf4j
public class ContractRepositoryImplementation {
    
    @Autowired
    private ContractRepository contractRepository;
    
    
    public Contract create(Contract contract) {
        log.info("Registering new user");
        return contractRepository.save(contract);
       
    }
    
    public List<Contract> getcontracts(){
        log.info("Fetching contract");
        return contractRepository.findAll();
    }
    
    public Contract getById(long id) {
        log.info("Getting the contract with " + id);
        return contractRepository.findById(id).get();
    }
    
    public List<Contract> findByClientId(Long clientId){
        log.info("Getting the contract with client id:" + clientId);
        return findByClientId(clientId);
    }
    
    public List<Users> findUsersByContractId(Long contractId){
        log.info("Getting the users with contract id:" + contractId);
        return contractRepository.findUsersByContractId(contractId);
    }
    
    @Transactional
    public Contract updateContract(Contract contract) {
        log.info("Updating contract ", contract);
        return contractRepository.save(contract);
    }
    
    public Boolean delete(long id) {
        log.info("Deleting contract by id: ", id );
        contractRepository.deleteById(id);
        return true;
    }


}
