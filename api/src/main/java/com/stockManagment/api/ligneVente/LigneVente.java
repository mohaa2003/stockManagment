package com.stockManagment.api.ligneVente;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.produit.Produit;
import com.stockManagment.api.vente.Vente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ligne_de_vente" )
public class LigneVente  extends SuperEntity {
    private Integer quantite = 1;
    @Column(name = "prix_de_vente" ,nullable = false)
    private Double prixVente; //placer le prix de produit ici

    @ManyToOne
    @JoinColumn(name = "id_vente",nullable = false)
    private Vente vente;

    @ManyToOne
    @JoinColumn(name = "id_produit",nullable = false)
    private Produit produit;

//    calculPrixLigneVente
}
