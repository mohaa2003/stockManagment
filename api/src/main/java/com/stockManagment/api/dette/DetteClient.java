package com.stockManagment.api.dette;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.client.Client;
import com.stockManagment.api.versement.Versement;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("CLIENT")
public class DetteClient extends Dette{

    @OneToMany(mappedBy = "dette")

    @OneToOne(mappedBy = "dette")
    private Client client;
}
