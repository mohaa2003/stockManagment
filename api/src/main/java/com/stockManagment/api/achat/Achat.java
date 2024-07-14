package com.stockManagment.api.achat;

import com.stockManagment.api.SuperEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "achat" )
public class Achat extends SuperEntity {
    private Double remiseTotale;
    private LocalDateTime tempsAchat;
    private MethodePayement methodePayement;

//    Double calculPrixFacture(){
//
//    }
}
