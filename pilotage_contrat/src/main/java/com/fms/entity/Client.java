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

public class Client extends AbstractElementsEntity{

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
