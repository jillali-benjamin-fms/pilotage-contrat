package com.fms.entity;

import java.time.LocalDateTime;
import java.util.Collection;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import enums.entity_enums.ContratAirbItServEnum;
import enums.entity_enums.ContratAirbusCatEnum;
import enums.entity_enums.ContratBUEnum;
import enums.entity_enums.ContratCatTPEnum;
import enums.entity_enums.ContratIntExtEnum;
import enums.entity_enums.ContratNouvEnum;
import enums.entity_enums.ContratProjetTPEnum;
import enums.entity_enums.ContratStatutEnum;
import enums.entity_enums.ContratTypeEnum;
import enums.entity_enums.ContratTypeOldEnum;
import enums.entity_enums.ContratUOEnum;
import enums.entity_enums.ContratVFEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Contract extends AbstractElementsEntity{
    /**
     * <p>Contrat ext?? <p>
     */
    private String contrat_ext;
    /**
     * <p>Contrat TP?? <p>
     */
    private String contrat_tp;
    /**
     * <p>Client de contrat. Liee avec l'id de client. <p>
     */
    @NotNull(message = "Le client doit pas etre vide")
    @ManyToOne (fetch = FetchType.LAZY, optional = false)  
    @JoinColumn(name = "client_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Client client;
    /**
     * <p>Montant de contrat. <p>
     */
    private double amount;
    /**
     * <p>Montant commande. <p>
     */
    private double ordered;
    /**
     * <p>l'invoicet. <p>
     */
    private double invoiced;
    
    /**
     * <p>Date de debut de contrat. <p>
     */
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime date_deb;
    
    /**
     * <p>Date fin de contrat. <p>
     */
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime date_fin;
    
    /**
     * <p>Statut de contrat. <p>
     */
    @NotNull(message = "Statut de contrat non selectionnes")
    @Enumerated(EnumType.STRING)   
    private ContratStatutEnum statut;
    
    /**
     * <p>Contrat probabilite? <p>
     */
    private double probabilite;
    /**
     * <p>Type de contrat. <p>
     */
    @NotNull(message = "Type de contrat non selectionnes")
    @Enumerated(EnumType.STRING)
    private ContratTypeEnum type;    
    
    /**
     * <p>Contrat nouveau?? <p>
     */
    @Enumerated(EnumType.STRING)
    private ContratNouvEnum nouveau;
    
    /**
     * <p>Contrat op_fms?? <p>
     */
    /** Might be manyToMany **/
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "contract")
    private Collection<Users> op_fms;
    
    /**
     * libre mail ou contact
     */
    /**
     * <p>Partenaire du contract?? <p>
     */
    private String partenaireContact;
    
    private double vise;
    
    private double vise_pondere;
    
    /**
     * <p>Prochaine facture?? <p>
     */
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime prochaineFactu;
    
    private int etp;
    
    @NotNull(message = "Precise si le contrat est interne ou externe.")
    @Enumerated(EnumType.STRING)
    private ContratIntExtEnum int_ext;
    
    @Enumerated(EnumType.STRING)
    private ContratUOEnum uo_defaut;
    
    @NotNull(message = "my_system doit etre vrai ou faux.")
    @Enumerated(EnumType.STRING)
    private ContratVFEnum my_system;
    
    @Enumerated(EnumType.STRING)
    private ContratBUEnum bu;
    
    @NotNull(message = "my_system doit etre vrai ou faux.")
    @Enumerated(EnumType.STRING)
    private ContratVFEnum airbus;
    
    @Enumerated(EnumType.STRING)   
    private ContratProjetTPEnum projet_tp;
    
    @Enumerated(EnumType.STRING)
    private ContratCatTPEnum categorie_tp;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime deb_po;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")    
    private LocalDateTime fin_po;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime min_livraison;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime max_livraison;
    
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDateTime commande;
    
    @Enumerated(EnumType.STRING)
    private ContratTypeOldEnum type_old;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbusCatEnum airbus_category;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbItServEnum airbus_it_serices_capability;
    
    private long amount_pondere;
    
    @PrePersist
    private void init() {
        setDate_deb(LocalDateTime.now());
    } 
    
    
    

}
























