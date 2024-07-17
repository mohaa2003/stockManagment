package com.stockManagment.api.produit;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.categorie.Categorie;
import com.stockManagment.api.ligneAchat.LigneAchat;
import com.stockManagment.api.ligneVente.LigneVente;
import com.stockManagment.api.vente.Vente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produit" )
public class Produit  extends SuperEntity {
    @Column(nullable = false)
    private String libele;
    private String marque;
    private String description;
    @Column(nullable = false)
    private Double prix;
    @Column(nullable = false)
    private Integer quantiteDisponible = 0;

    @OneToMany(mappedBy = "produit")
    private List<LigneAchat> ligneAchatList ;

    @OneToMany(mappedBy = "produit")
    private List<LigneVente> ligneVenteList ;

    @ManyToOne
    @JoinColumn(name = "id_categorie",nullable = false)
    private Categorie categorie;
}
