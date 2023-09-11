package repositoryServices;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fms.entity.Client;

import jakarta.transaction.Transactional;
/**
 * 
 * @author FMS
 * <p>Client repository interface qui extends le JpaRepository<p>.
 *
 */
@Repository
@Transactional
public interface ClientRepository extends JpaRepository<Client, Long> {

//    Client create(Client client);
    
//    @Query("select c from Client where c.clientBuyer = ?1")
//    Optional<Client> findClientsByClientBuyer(String clientBuyer);
//    
//    @Query("select c from Client where c.contractManager = ?1")
//    Optional<Client> findClientsByContractManager(String contractManager);
//    
//    @Query("select c from Client where c.bundleManager = ?1")
//    Optional<Client> findClientsByBundleManager(String contractManager);
    

}
