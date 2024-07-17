package com.stockManagment.api.agent;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.transaction.Transaction;
import com.stockManagment.api.vente.Vente;
import com.stockManagment.api.versement.VersementAutre;
import com.stockManagment.api.versement.VersementClient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "agent" )
public class Agent extends SuperEntity
{
    @Column(name = "nom_agent" ,nullable = false)
    private String nomAgent;
    @Column(name = "surnom_agent")
    private String surnomAgent;
    @Column(name = "num_de_tlph_a" )
    private String numTlph;
    private String fonction;

    @OneToMany(mappedBy = "agent")
    private List<Transaction> transactionList ;

    @OneToMany(mappedBy = "agent")
    private List<VersementAutre> versementAutreList ;

//    date de 1er achat ... et de dernier achat
}
