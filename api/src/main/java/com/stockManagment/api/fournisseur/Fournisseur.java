package com.stockManagment.api.fournisseur;

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
@Table(name = "fournisseur" )
public class Fournisseur  extends SuperEntity {
    private String nomFournisseur;
    private String surnomFournisseur;
}
