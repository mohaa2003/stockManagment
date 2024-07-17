package com.stockManagment.api.versement;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.dette.DetteAutre;
import com.stockManagment.api.dette.DetteFournisseur;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class VersementAutre extends Versement{
    @ManyToOne
    @JoinColumn(name = "id_dette",nullable = false)
    private DetteAutre detteAutre;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;
}
