package com.stockManagment.api.versement;

import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteFournisseur;
import com.stockManagment.api.fournisseur.Fournisseur;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("FOURNISSEUR")
public class VersementFournisseur extends Versement{

    @ManyToOne
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur",nullable = false)
    private Fournisseur fournisseur;
}
