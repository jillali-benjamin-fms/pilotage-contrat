package com.fms.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractElementsEntity implements Serializable{
	/**
	 * <p> c'est l'id d'element avec des number genere automatiquements des numero.</p>
	 */
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "id", sequenceName = "id_sequence")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	protected Long id;
	
	/**
	 * <p>La date de creation<p>
	 */
//	protected LocalDateTime createdOn;
	
	/**
	 * <p>obtient la date de création d'un élément avec PrePersist</p>
	 */
//	@PrePersist
//	private void init() {
//		setCreatedOn(LocalDateTime.now());
//	}
	

}
