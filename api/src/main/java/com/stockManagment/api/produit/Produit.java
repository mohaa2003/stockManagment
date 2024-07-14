package com.stockManagment.api.produit;

import com.stockManagment.api.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produit" )
public class Produit  extends SuperEntity {
    private String nom;
    private Double prixUnitaire;
    private Integer quantiteDisponible = 0 ;

}
