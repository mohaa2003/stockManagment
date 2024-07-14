package com.stockManagment.api.compte;

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
@Table(name = "compte" )
public class Compte  extends SuperEntity {
    private String nomCompte;
    private Double credit;
}
