package com.stockManagment.api.produit;

import com.stockManagment.api.categorie.CategorieDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.ligneAchat.LigneAchatDto;
import com.stockManagment.api.ligneVente.LigneVenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitDto {
    private Integer id;
    private String libele;
    private String marque;
    private String description;
    private Double prix;
    private Integer quantiteDisponible;
    private List<LigneAchatDto> ligneAchatList;
    private List<LigneVenteDto> ligneVenteList;
    private CategorieDto categorie;
    private EntrepriseDto entreprise;

    public static ProduitDto fromEntity(Produit produit) {
        if (produit == null) {
            return null;
        }

        return new ProduitDto(
                produit.getId(),
                produit.getLibele(),
                produit.getMarque(),
                produit.getDescription(),
                produit.getPrix(),
                produit.getQuantiteDisponible(),
                produit.getLigneAchatList().stream().map(LigneAchatDto::fromEntity).collect(Collectors.toList()),
                produit.getLigneVenteList().stream().map(LigneVenteDto::fromEntity).collect(Collectors.toList()),
                CategorieDto.fromEntity(produit.getCategorie()),
                EntrepriseDto.fromEntity(produit.getEntreprise())
        );
    }

    public static Produit toEntity(ProduitDto produitDto) {
        if (produitDto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setLibele(produitDto.getLibele());
        produit.setMarque(produitDto.getMarque());
        produit.setDescription(produitDto.getDescription());
        produit.setPrix(produitDto.getPrix());
        produit.setQuantiteDisponible(produitDto.getQuantiteDisponible());
        produit.setLigneAchatList(produitDto.getLigneAchatList().stream().map(LigneAchatDto::toEntity).collect(Collectors.toList()));
        produit.setLigneVenteList(produitDto.getLigneVenteList().stream().map(LigneVenteDto::toEntity).collect(Collectors.toList()));
        produit.setCategorie(CategorieDto.toEntity(produitDto.getCategorie()));
        produit.setEntreprise(EntrepriseDto.toEntity(produitDto.getEntreprise()));

        return produit;
    }
}
