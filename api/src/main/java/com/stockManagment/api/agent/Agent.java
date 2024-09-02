package com.stockManagment.api.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.transaction.Transaction;
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
    @JsonIgnore
    private List<Transaction> transactionList ;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;

//    date de 1er achat ... et de dernier achat
}
