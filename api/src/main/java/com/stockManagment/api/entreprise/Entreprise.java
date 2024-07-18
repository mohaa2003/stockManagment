package com.stockManagment.api.entreprise;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.client.Client;
import com.stockManagment.api.compte.Compte;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.fournisseur.Fournisseur;
import com.stockManagment.api.produit.Produit;
import com.stockManagment.api.transaction.Transaction;
import com.stockManagment.api.utilisateur.Utilisateur;
import com.stockManagment.api.vente.Vente;
import com.stockManagment.api.versement.VersementFournisseur;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entreprise" )
public class Entreprise  extends SuperEntity {
    @Column(name = "nom_entreprise" ,nullable = false,unique = true)
    private String nomEntreprise;

    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    private List<Utilisateur> utilisateurList ;

    @OneToMany(mappedBy = "entreprise")
    private List<Produit> produitList;

    @OneToMany(mappedBy = "entreprise")
    private List<Fournisseur> fournisseurList;

    @OneToMany(mappedBy = "entreprise")
    private List<Client> clientList;

    @OneToMany(mappedBy = "entreprise")
    private List<Agent> agentList;

    @OneToMany(mappedBy = "entreprise")
    private List<Vente> venteList;

    @OneToMany(mappedBy = "entreprise")
    private List<Achat> achatList;

    @OneToMany(mappedBy = "entreprise")
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "entreprise")
    private List<Compte> compteList;

    @OneToMany(mappedBy = "entreprise")
    private List<Dette> detteList;
}
