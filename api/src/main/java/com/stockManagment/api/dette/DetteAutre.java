package com.stockManagment.api.dette;

import com.stockManagment.api.agent.Agent;
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
@DiscriminatorValue("AUTRE")
public class DetteAutre extends Dette{

    @OneToMany(mappedBy = "dette")
    private List<Versement> versementList;

    @OneToOne(mappedBy = "dette")
    private Agent agent;
}
