package com.stockManagment.api.ligneVente;

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
@Table(name = "ligne_de_vente" )
public class LigneVente  extends SuperEntity {
    private Double quantite;
    private Double prixVente;

//    calculPrixLigneVente
}
