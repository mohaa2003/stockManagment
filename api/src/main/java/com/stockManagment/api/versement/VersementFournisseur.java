package com.stockManagment.api.versement;

import com.stockManagment.api.dette.DetteFournisseur;
import com.stockManagment.api.fournisseur.Fournisseur;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class VersementFournisseur extends Versement{

    @ManyToOne
    @JoinColumn(name = "id_dette")
    private DetteFournisseur detteFournisseur;

    @ManyToOne
    @JoinColumn(name = "id_fournisseur",nullable = false)
    private Fournisseur fournisseur;
}
