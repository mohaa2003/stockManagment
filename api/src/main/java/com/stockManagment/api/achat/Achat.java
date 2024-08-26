package com.stockManagment.api.achat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.ligneAchat.LigneAchat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "achat" )

public class Achat extends SuperEntity {
    @Column(name = "prix_achat")
    private Double prixAchatTotal= this.getPrixAchatTotal() ;
    @Column(name = "prix_remise_achat")
    private Double prixApresRemise= this.getPrixAchatTotal() ;
    @Column(name = "some_paye")
    private Double somePaye = 0.0;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_de_laivraison")
    private StatutLivraison statutLivraison;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_de_payement",nullable = false)
    private StatutPayement statutPayement = StatutPayement.AVEC_DETTE;
    private String comment;

    @OneToMany(mappedBy = "achat",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LigneAchat> ligneAchatList ;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur",nullable = false)
    private Fournisseur fournisseur;

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
