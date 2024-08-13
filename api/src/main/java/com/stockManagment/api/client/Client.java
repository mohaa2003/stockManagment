package com.stockManagment.api.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.entreprise.Entreprise;
import com.stockManagment.api.vente.Vente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client  extends SuperEntity {
    @Column(name = "nom_de_client" ,nullable = false)
    private String nomClient;
    @Column(name = "surnom_de_client" )
    private String surnomClient;
    @Column(name = "num_de_tlph_c" )
    private String numTlph;
    @Column(name = "fonction" )
    private String fonction;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Vente> venteList ;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_dette")
    private Dette dette;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_entreprise",nullable = false)
    private Entreprise entreprise;

//    date de 1er achat ... et de dernier achat
}