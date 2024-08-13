package com.stockManagment.api.ligneVente;

import com.stockManagment.api.produit.ProduitDto;
import com.stockManagment.api.vente.VenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneVenteDto {
    private Integer id;
    private Integer quantite;
    private Double prixVente;
    private VenteDto vente;
    private ProduitDto produit;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {
        if (ligneVente == null) {
            return null;
        }

        return new LigneVenteDto(
                ligneVente.getId(),
                ligneVente.getQuantite(),
                ligneVente.getPrixVente(),
                VenteDto.fromEntity(ligneVente.getVente()),
                ProduitDto.fromEntity(ligneVente.getProduit())
        );
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {
        if (ligneVenteDto == null) {
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixVente(ligneVenteDto.getPrixVente());
        ligneVente.setVente(VenteDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setProduit(ProduitDto.toEntity(ligneVenteDto.getProduit()));

        return ligneVente;
    }

    public Double calculPrixLigne(){
        return Double.valueOf(this.quantite * this.prixVente);
    }
}
