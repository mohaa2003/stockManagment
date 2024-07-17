package com.stockManagment.api.client;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.produit.Produit;
import com.stockManagment.api.vente.Vente;
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
    private List<Vente> venteList ;

    @OneToMany(mappedBy = "client")
    private List<VersementClient> versementClientList ;

//    date de 1er achat ... et de dernier achat
}