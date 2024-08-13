package com.stockManagment.api.fournisseur;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.dette.Dette;
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
@Table(name = "fournisseur" )
public class Fournisseur  extends SuperEntity {
    @Column(name = "nom_de_fournisseur" ,nullable = false)
    private String nomFournisseur;
    @Column(name = "surnom_de_fournisseur" )
    private String surnomFournisseur;
    @Column(name = "num_de_tlph_f")
    private String numTlph;

    @OneToMany(mappedBy = "fournisseur")
    @JsonIgnore
    private List<Achat> achatList ;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;
}
