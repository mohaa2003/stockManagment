package com.stockManagment.api.dette;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.entreprise.Entreprise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dette" )
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Dette  extends SuperEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_dette" ,nullable = false)
    private DetteType typeDette;
    @Column(name = "some" ,nullable = false)
    private double some;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;
}
