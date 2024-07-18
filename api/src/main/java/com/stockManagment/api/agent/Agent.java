package com.stockManagment.api.agent;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.dette.DetteAutre;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.transaction.Transaction;
import com.stockManagment.api.vente.Vente;
import com.stockManagment.api.versement.VersementAutre;
import com.stockManagment.api.versement.VersementClient;
import jakarta.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dette_id")
    private DetteAutre detteAutre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise")
    private Entreprise entreprise;

//    date de 1er achat ... et de dernier achat
}
