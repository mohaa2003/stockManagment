package com.stockManagment.api.achat;

import com.stockManagment.api.agent.Agent;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.compte.TypeCompte;
import com.stockManagment.api.dette.DetteAutreDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.fournisseur.FournisseurDto;
import com.stockManagment.api.ligneAchat.LigneAchatDto;
import com.stockManagment.api.transaction.TransactionDto;
import com.stockManagment.api.versement.VersementAutreDto;
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

        Achat achat = new Achat();

                achat.setId(achatDto.getId());
                achat.setPrixAchatTotal(achatDto.getPrixAchatTotal());
                achat.setSomePaye(achatDto.getSomePaye());
                achat.setTypeComptePayementInitial(achatDto.getTypeComptePayementInitial());
                achat.setStatutLivraison(achatDto.getStatutLivraison());
                achat.setStatutPayement(achatDto.getStatutPayement());
                achat.setLigneAchatList(achatDto.getLigneAchatList().stream().map(LigneAchatDto::toEntity).collect(Collectors.toList()));
                achat.setFournisseur(FournisseurDto.toEntity(achatDto.getFournisseur()));
                achat.setCompte(CompteDto.toEntity(achatDto.getCompte()));
                achat.setEntreprise(EntrepriseDto.toEntity(achatDto.getEntreprise()));

                return achat;
    }
}
