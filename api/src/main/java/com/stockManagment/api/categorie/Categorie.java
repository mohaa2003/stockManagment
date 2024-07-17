package com.stockManagment.api.categorie;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.ligneAchat.LigneAchat;
import com.stockManagment.api.produit.Produit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categorie" )
public class Categorie extends SuperEntity {
    @Column(name = "nom_de_categorie",nullable = false)
    private String nomCategorie;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produitList ;

    @OneToMany(mappedBy = "categorie")
    private List<Categorie> categorieList ;   //pour les categories imbriquees

    @ManyToOne
    @JoinColumn(name = "id_categorie")
    private Categorie categorie;
}
