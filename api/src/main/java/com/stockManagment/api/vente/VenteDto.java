package com.stockManagment.api.vente;

import com.stockManagment.api.achat.StatutLivraison;
import com.stockManagment.api.achat.StatutPayement;
import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.ligneVente.LigneVenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VenteDto {
    private Integer id;
    private Double prixTotaleVente;
    private Double prixApresRemise;
    private Double somePaye;
    private StatutLivraison statutLivraison;
    private StatutPayement statutPayement;
    private List<LigneVenteDto> ligneVenteList;
    private ClientDto client;
    private CompteDto compte;
    private EntrepriseDto entreprise;

    public static VenteDto fromEntity(Vente vente) {
        if (vente == null) {
            return null;
        }

        return new VenteDto(
                vente.getId(),
                vente.getPrixTotaleVente(),
                vente.getPrixApresRemise(),
                vente.getSomePaye(),
                vente.getStatutLivraison(),
                vente.getStatutPayement(),
                vente.getLigneVenteList().stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList()),
                ClientDto.fromEntity(vente.getClient()),
                CompteDto.fromEntity(vente.getCompte()),
                EntrepriseDto.fromEntity(vente.getEntreprise())
        );
    }

    public static Vente toEntity(VenteDto venteDto) {
        if (venteDto == null) {
            return null;
        }

        Vente vente = new Vente();
        vente.setId(venteDto.getId());
        vente.setPrixTotaleVente(venteDto.getPrixTotaleVente());
        vente.setPrixApresRemise(venteDto.getPrixApresRemise());
        vente.setSomePaye(venteDto.getSomePaye());
        vente.setStatutPayement(venteDto.getStatutPayement());
        vente.setStatutLivraison(venteDto.getStatutLivraison());
        vente.setLigneVenteList(venteDto.getLigneVenteList().stream().map(LigneVenteDto::toEntity).collect(Collectors.toList()));
        vente.setClient(ClientDto.toEntity(venteDto.getClient()));
        vente.setCompte(CompteDto.toEntity(venteDto.getCompte()));
        vente.setEntreprise(EntrepriseDto.toEntity(venteDto.getEntreprise()));

        return vente;
    }

    public Double calculPrixVente(){
        return( this.ligneVenteList.stream().map(LigneVenteDto::calculPrixLigne).reduce(0.0,Double::sum) );
    }

    public Double faireRemise(Double remise){
        this.prixApresRemise = this.prixTotaleVente - remise;
        return remise;
    }
}
