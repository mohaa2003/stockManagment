package com.stockManagment.api.fournisseur;

import com.stockManagment.api.SuperEntity;
import com.stockManagment.api.achat.Achat;
import com.stockManagment.api.vente.Vente;
import com.stockManagment.api.versement.VersementFournisseur;
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
@Table(name = "fournisseur" )
public class Fournisseur  extends SuperEntity {
    @Column(name = "nom_de_fournisseur" ,nullable = false)
    private String nomFournisseur;
    @Column(name = "surnom_de_fournisseur" )
    private String surnomFournisseur;
    @Column(name = "num_de_tlph_f")
    private String numTlph;

    @OneToMany(mappedBy = "fournisseur")
    private List<Achat> achatList ;

    @OneToMany(mappedBy = "fournisseur")
    private List<VersementFournisseur> versementFournisseurList ;
}
