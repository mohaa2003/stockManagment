package com.stockManagment.api.entreprise;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.utilisateur.Utilisateur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entreprise" )
public class Entreprise  extends SuperEntity {
    @Column(name = "nom_entreprise" ,nullable = false,unique = true)
    private String nomEntreprise;

    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurList ;
}
