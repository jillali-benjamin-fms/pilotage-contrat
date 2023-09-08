package com.fms.entity;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter

//@NamedQuery(name = Client.FIND_ALL_CLIENTS, query = "Select c from Client")
//@NamedQuery(name = Client.FIND_CLIENTS_BY_ID, query = "Select c from Client where c.id = :id")
//@NamedQuery(name = Client.FIND_CLIENTS_BY_CONTRACT, query = "Select c from Client where c.fmsContract = :fmsContract")
//@NamedQuery(name = Client.FIND_ALL_CLIENTS_BY_BUYER, query = "Select c from Client where c.clientBuyer = :clientBuyer")
//@NamedQuery(name = Client.FIND_ALL_CLIENTS_BY_CONTRACT_MANAGER, query = "Select c from Client where c.contractManager = :contractManager")
//@NamedQuery(name = Client.FIND_ALL_CLIENTS_BY_BUNDLE_MANAGER, query = "Select c from Client where c.bundleManager = :bundleManager")
//	/**
//	 * <p>les constantes des requêtes <p>
//	 */
public class Client extends AbstractElementsEntity{
	/**
	 * <p>les constantes des requêtes <p>
	 */
//	public static final String FIND_ALL_CLIENTS = "Client.findAllClients";
//	public static final String FIND_CLIENTS_BY_ID = "Client.findClientsById";
//	public static final String FIND_CLIENTS_BY_CONTRACT = "Client.findClientsByContract";	
//	public static final String FIND_ALL_CLIENTS_BY_BUYER = "Client.findAllClientsByBuyer";
//	public static final String FIND_ALL_CLIENTS_BY_CONTRACT_MANAGER = "Client.findAllClientsByContractManager";
//	public static final String FIND_ALL_CLIENTS_BY_BUNDLE_MANAGER = "Client.findAllClientsByBundleManager";
	
	/**
	 * <p>Le contrat de client avec fms <p>
	 */	
//	@OneToMany
//	private Collection<Contract> fmsContract;
	
	/**
	 * <p>Je sais pas trops <p>
	 */		
	private String clientBuyer;
	
	/**
	 * <p>Le manager de contract <p>
	 */	
	private String contractManager;
	
	/**
	 * <p>Je sais pas trops <p>
	 */		
	private String bundleManager;

}
