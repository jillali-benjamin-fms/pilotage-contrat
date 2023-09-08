package repositoryServices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fms.entity.Contract;

public interface ContractRepository extends JpaRepository<Contract, Long> {

}
