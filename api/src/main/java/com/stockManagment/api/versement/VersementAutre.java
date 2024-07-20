package com.stockManagment.api.versement;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.dette.Dette;
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
@DiscriminatorValue("AUTRE")
public class VersementAutre extends Versement{
    @ManyToOne
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne
    @JoinColumn(name = "id_agent")
    private Agent agent;
}
