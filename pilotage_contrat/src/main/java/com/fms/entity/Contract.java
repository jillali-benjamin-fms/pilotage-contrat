package com.fms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fms.enums.entity_enums.ContratAirbItServEnum;
import com.fms.enums.entity_enums.ContratAirbusCatEnum;
import com.fms.enums.entity_enums.ContratBUEnum;
import com.fms.enums.entity_enums.ContratCatTPEnum;
import com.fms.enums.entity_enums.ContratIntExtEnum;
import com.fms.enums.entity_enums.ContratNouvEnum;
import com.fms.enums.entity_enums.ContratProjetTPEnum;
import com.fms.enums.entity_enums.ContratStatutEnum;
import com.fms.enums.entity_enums.ContratTypeEnum;
import com.fms.enums.entity_enums.ContratTypeOldEnum;
import com.fms.enums.entity_enums.ContratUOEnum;
import com.fms.enums.entity_enums.ContratVFEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    //@ManyToOne (cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, optional = false)
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
   // @JoinColumn(name = "client_id", nullable = false)
  //  @OnDelete(action = OnDeleteAction.CASCADE)
   // @JsonIgnore
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate date_deb;
    
    /**
     * <p>Date fin de contrat. <p>
     */
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate date_fin;
    
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
   // @Column(name = "opFms")
    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //ManyToMany
    //@JoinColumn(name = "user_id")
   // @ManyToMany(mappedBy = "contract", fetch = FetchType.LAZY)
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinTable(name = "contract_user", joinColumns = @JoinColumn(name = "contract_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<Users> opFms;
    
    
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
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate prochaineFactu;
    
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
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate deb_po;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")    
    private LocalDate fin_po;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate min_livraison;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate max_livraison;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern="dd-MM-yyyy")
    private LocalDate commande;
    
    @Enumerated(EnumType.STRING)
    private ContratTypeOldEnum type_old;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbusCatEnum airbus_category;
    
    @Enumerated(EnumType.STRING)
    private ContratAirbItServEnum airbus_it_services_capability;
    
    private long amount_pondere;
    
//    @PrePersist
//    private void init() {
//        setDate_deb(LocalDateTime.now());
//    }
    
    public void addOpFms(Users user) {
        this.opFms.add(user);
    }
    
    public void removeOpFms(long userId) {
        Users opFms = this.opFms.stream().filter(t -> t.getId() == userId).findFirst().orElse(null);
        if (opFms != null) {
          this.opFms.remove(opFms);
          opFms.getContracts().remove(this);
        }
      }
    
    
    

}
























