package com.stockManagment.api.versement;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.compte.Compte;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Versement extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_dette" ,nullable = false)
    private DetteType typeDette;
    @Column(nullable = false)
    private double some;

    @ManyToOne
    @JoinColumn(name = "id_compte",nullable = false)
    private Compte compte;
}
