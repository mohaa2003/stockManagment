package com.stockManagment.api.compte;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.transaction.Transaction;
import com.stockManagment.api.vente.Vente;
import com.stockManagment.api.versement.Versement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "compte")
public class Compte  extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "nom_de_compte" ,nullable = false,unique = true)
    private TypeCompte nomCompte;
    @Column(name = "credit" ,nullable = false,unique = true)
    private Double credit;

    //datteCreation et modification

    @OneToMany(mappedBy = "compte")
    @JsonIgnore
    private List<Versement> versementList;

    @OneToMany(mappedBy = "compte")
    @JsonIgnore
    private List<Vente> venteList;

    @OneToMany(mappedBy = "compte")
    @JsonIgnore
    private List<Achat> achatList;

    @OneToMany(mappedBy = "compte")
    @JsonIgnore
    private List<Transaction> transaction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;


}
