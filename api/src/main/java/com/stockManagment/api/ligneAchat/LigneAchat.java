package com.stockManagment.api.ligneAchat;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.produit.Produit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ligne_dachat" )
public class LigneAchat  extends SuperEntity {
    private Integer quantite = 1;
    @Column(name = "prix_achat" ,nullable = false)
    private Double prixAchat; //initialiser le prix par le prix initiale de produit qui va etre en relation avec cette ligne d'achat

    @ManyToOne
    @JoinColumn(name = "id_achat",nullable = false)
    private Achat achat;

    @ManyToOne
    @JoinColumn(name = "id_produit",nullable = false)
    private Produit produit;

}
