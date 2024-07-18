package com.stockManagment.api.vente;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.client.Client;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.compte.TypeCompte;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.ligneVente.LigneVente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vente" )
public class Vente  extends SuperEntity {
    @Column(name = "prix_de_vente", nullable = false)
    private Double prixTotaleVente;
    @Enumerated(EnumType.STRING)
    @Column(name = "type_payement_initial", nullable = false)
    private TypeCompte typeComptePayementInitial;

    @OneToMany(mappedBy = "vente",cascade = CascadeType.ALL)
    private List<LigneVente> ligneVenteList ;

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_compte",nullable = false)
    private Compte compte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;

//    Double calculPrixFacture(){
//
//    }
}
