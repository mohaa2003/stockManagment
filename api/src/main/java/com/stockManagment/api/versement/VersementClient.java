package com.stockManagment.api.versement;

import com.stockManagment.api.client.Client;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.dette.DetteClient;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CLIENT")
public class VersementClient extends Versement{

    @ManyToOne
    @JoinColumn(name = "id_client",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_dette")
    private DetteClient detteClient;
}
