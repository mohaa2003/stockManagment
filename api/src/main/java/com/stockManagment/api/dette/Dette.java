package com.stockManagment.api.dette;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.versement.Versement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dette" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dette_type", discriminatorType = DiscriminatorType.STRING)
public class Dette  extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "some" ,nullable = false)
    private double some;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;

    @OneToMany(mappedBy = "dette")
    private List<Versement> versementList;
}
