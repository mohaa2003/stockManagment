package com.stockManagment.api.ligneAchat;

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
public class LigneAchat  extends SuperEntity {
    private Double quantite;
    private Double prixAchat;

//    calculPrixLigneAchat
}
