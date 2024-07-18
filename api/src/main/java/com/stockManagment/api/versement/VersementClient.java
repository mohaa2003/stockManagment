package com.stockManagment.api.versement;

import com.stockManagment.api.client.Client;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.dette.DetteClient;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class VersementClient extends Versement{

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_dette")
    private DetteClient detteClient;
}
