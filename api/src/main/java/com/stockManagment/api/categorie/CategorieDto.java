package com.stockManagment.api.categorie;

import com.stockManagment.api.produit.ProduitDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieDto {
    private Integer id;
    private String nomCategorie;
    private List<ProduitDto> produitList;
    private List<CategorieDto> categorieList;
    private Integer categorieId; // Référence vers la catégorie parente

    public static CategorieDto fromEntity(Categorie categorie) {
        if (categorie == null) {
            return null;
        }

        return new CategorieDto(
                categorie.getId(),
                categorie.getNomCategorie(),
                categorie.getProduitList().stream().map(ProduitDto::fromEntity).collect(Collectors.toList()),
                categorie.getCategorieList().stream().map(CategorieDto::fromEntity).collect(Collectors.toList()),
                categorie.getCategorie() != null ? categorie.getCategorie().getId() : null
        );
    }

    public static Categorie toEntity(CategorieDto categorieDto) {
        if (categorieDto == null) {
            return null;
        }

        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setNomCategorie(categorieDto.getNomCategorie());
        categorie.setProduitList(categorieDto.getProduitList().stream().map(ProduitDto::toEntity).collect(Collectors.toList()));
        categorie.setCategorieList(categorieDto.getCategorieList().stream().map(CategorieDto::toEntity).collect(Collectors.toList()));

        // Remarque : la catégorie parente (categorie) ne sera pas initialisée ici pour éviter la boucle infinie de récursion
        // Il peut être nécessaire de gérer cela d'une manière spécifique en fonction de la logique de votre application.

        return categorie;
    }
}
