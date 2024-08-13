package com.stockManagment.api.utilisateur;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.entreprise.Entreprise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur" )
public class Utilisateur extends SuperEntity {
    @Column(name = "nom_utilisateur", nullable = false)
    private String username;
    @Column(name = "mot_de_passe", nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Roles role;
    //private static Integer nombreUtilisateur;  a mettre dans le dto pour ne pas entrer dans

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;
}
