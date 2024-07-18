package com.stockManagment.api.dette;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.client.Client;
import com.stockManagment.api.versement.VersementAutre;
import com.stockManagment.api.versement.VersementClient;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;

public class DetteClient extends Dette{

    @OneToMany(mappedBy = "dette")
    private List<VersementClient> versementClientList;

    @OneToOne(mappedBy = "dette")
    private Client client;
}
