package com.fms.entity;

import java.time.LocalDateTime;
import java.util.Collection;

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
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Contract extends AbstractElementsEntity{
    
    private String contrat_ext;
    
    private String contrat_tp;
    
    @ManyToOne    
    private Client client;
    
    private double amount;
    
    private double ordered;
    
    private double invoiced;
    
    private LocalDateTime date_deb;
    
    private LocalDateTime date_fin;
    
    @NotNull(message = "Statut de contrat non selectionnes")
    @Enumerated(EnumType.STRING)   
    private ContratStatutEnum statut;
    
    private double probabilite;
    
    @NotNull(message = "Type de contrat non selectionnes")
    @Enumerated(EnumType.STRING)
    private ContratTypeEnum type;
    
    /**
     * must ask about this one if comment must have contract then better to setup the relationshop in the comment with manytoone link with contract
     */
    @OneToMany
    private Collection<Comment> commentaire;
    
    @Enumerated(EnumType.STRING)
    private ContratNouvEnum nouveau;
    
    /** Might be manyToMany **/
    @OneToMany
    private Collection<Users> op_fms;
    
    /**
     * libre mail ou contact
     */
    private String partenaireContact;
    
    private double vise;
    
    private double vise_pondere;
    
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
    
    private LocalDateTime deb_po;
    
    private LocalDateTime fin_po;
    
    private LocalDateTime min_livraison;
    
    private LocalDateTime max_livraison;
    
    private LocalDateTime commande;
    
    @Enumerated(EnumType.STRING)
    private ContratTypeOldEnum type_old;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbusCatEnum airbus_category;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbItServEnum airbus_it_serices_capability;
    
    private long amount_pondere;
    
    
    
    
    
    

}
























