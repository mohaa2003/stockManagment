package com.stockManagment.api.achat;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.TypeCompte;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.fournisseur.FournisseurDto;
import com.stockManagment.api.ligneAchat.LigneAchatDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AchatDto {
    private Integer id;
    private Double prixAchatTotal;
    private Double somePaye;
    private TypeCompte typeComptePayementInitial;
    private StatutLivraison statutLivraison;
    private StatutPayement statutPayement;
    private List<LigneAchatDto> ligneAchatList;
    private FournisseurDto fournisseur;
    private CompteDto compte;
    private EntrepriseDto entreprise;

    public static AchatDto fromEntity(Achat achat) {
        if (achat == null) {
            return null;
        }

        return new AchatDto(
                achat.getId(),
                achat.getPrixAchatTotal(),
                achat.getSomePaye(),
                achat.getTypeComptePayementInitial(),
                achat.getStatutLivraison(),
                achat.getStatutPayement(),
                achat.getLigneAchatList().stream().map(LigneAchatDto::fromEntity).collect(Collectors.toList()),
                FournisseurDto.fromEntity(achat.getFournisseur()),
                CompteDto.fromEntity(achat.getCompte()),
                EntrepriseDto.fromEntity(achat.getEntreprise())
        );
    }

    public static Achat toEntity(AchatDto achatDto) {
        if (achatDto == null) {
            return null;
        }

        return new Achat(
                achatDto.getId(),
                achatDto.getPrixAchatTotal(),
                achatDto.getSomePaye(),
                achatDto.getTypeComptePayementInitial(),
                achatDto.getStatutLivraison(),
                achatDto.getStatutPayement(),
                achatDto.getLigneAchatList().stream().map(LigneAchatDto::toEntity).collect(Collectors.toList()),
                FournisseurDto.toEntity(achatDto.getFournisseur()),
                CompteDto.toEntity(achatDto.getCompte()),
                EntrepriseDto.toEntity(achatDto.getEntreprise())
        );
    }
}
