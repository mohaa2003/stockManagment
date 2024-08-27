package com.stockManagment.api.fournisseur;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.dette.DetteFournisseurDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDto {
    private Integer id;
    private Boolean isDeleted;
    private String nomFournisseur;
    private String surnomFournisseur;
    private String numTlph;
    private List<AchatDto> achatList;
    private DetteFournisseurDto detteFournisseur;
    private EntrepriseDto entreprise;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        }

        return new FournisseurDto(
                fournisseur.getId(),
                fournisseur.getIsDeleted(),
                fournisseur.getNomFournisseur(),
                fournisseur.getSurnomFournisseur(),
                fournisseur.getNumTlph(),
                fournisseur.getAchatList().stream().map(AchatDto::fromEntity).collect(Collectors.toList()),
                (DetteFournisseurDto) DetteFournisseurDto.fromEntity(fournisseur.getDette()),
                EntrepriseDto.fromEntity(fournisseur.getEntreprise())
        );
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {
        if (fournisseurDto == null) {
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setIsDeleted(fournisseurDto.getIsDeleted());
        fournisseur.setNomFournisseur(fournisseurDto.getNomFournisseur());
        fournisseur.setSurnomFournisseur(fournisseurDto.getSurnomFournisseur());
        fournisseur.setNumTlph(fournisseurDto.getNumTlph());
        fournisseur.setAchatList(fournisseurDto.getAchatList().stream().map(AchatDto::toEntity).collect(Collectors.toList()));
        fournisseur.setDette(DetteFournisseurDto.toEntity(fournisseurDto.getDetteFournisseur()));
        fournisseur.setEntreprise(EntrepriseDto.toEntity(fournisseurDto.getEntreprise()));

        return fournisseur;
    }
}
