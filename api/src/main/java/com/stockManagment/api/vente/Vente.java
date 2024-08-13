package com.stockManagment.api.vente;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Double prixTotaleVente = this.getPrixTotaleVente();
    @Column(name = "prix_remise_vente", nullable = false)
    private Double prixApresRemise = this.getPrixTotaleVente();
    @Enumerated(EnumType.STRING)

    @OneToMany(mappedBy = "vente",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LigneVente> ligneVenteList ;

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_compte",nullable = false)
    private Compte compte;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;

//    Double calculPrixFacture(){
//
//    }
}
