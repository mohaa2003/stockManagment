package com.stockManagment.api.versement;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "versement" )
public class Versement extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_versement" ,nullable = false)
    private DetteType typeVersement;
    @Column(nullable = false)
    private Double some;


    @ManyToOne
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne
    @JoinColumn(name = "id_compte",nullable = false)
    private Compte compte;
}
