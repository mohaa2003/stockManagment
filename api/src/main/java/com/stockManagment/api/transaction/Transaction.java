package com.stockManagment.api.transaction;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.entreprise.Entreprise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction" )
public class Transaction  extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_transaction", nullable = false)
    private TypeTransaction typeTransaction;
    @Column(nullable = false,unique = true)
    private Double intitule;
    @Column( nullable = false)
    private Double prix;
    @Column(name = "prix_paye", nullable = false)
    private Double prixPaye = this.prix;  //si le prix paye inferieur de prix un classe de type dette va etre creee
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_agent")  //not required !
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "id_compte",nullable = false)
    private Compte compte;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}
