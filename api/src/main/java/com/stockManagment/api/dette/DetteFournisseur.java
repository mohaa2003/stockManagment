package com.stockManagment.api.dette;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.versement.VersementAutre;
import com.stockManagment.api.versement.VersementFournisseur;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class DetteFournisseur extends Dette{

    @OneToMany(mappedBy = "dette")
    private List<VersementFournisseur> versementFournisseurList;

    @OneToOne(mappedBy = "dette")
    private Fournisseur fournisseur;
}
