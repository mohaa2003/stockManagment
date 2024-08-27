package com.stockManagment.api.compte;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.transaction.TransactionDto;
import com.stockManagment.api.vente.VenteDto;
import com.stockManagment.api.versement.VersementDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteDto {
    private Integer id;
    private Boolean isDeleted;
    private TypeCompte nomCompte;
    private Double credit;
    private List<VersementDto> versementList;
    private List<VenteDto> venteList;
    private List<AchatDto> achatList;
    private List<TransactionDto> transactionList;
    private EntrepriseDto entreprise;

    public static CompteDto fromEntity(Compte compte) {
        if (compte == null) {
            return null;
        }

        return new CompteDto(
                compte.getId(),
                compte.getIsDeleted(),
                compte.getNomCompte(),
                compte.getCredit(),
                compte.getVersementList().stream().map(VersementDto::fromEntity).collect(Collectors.toList()),
                compte.getVenteList().stream().map(VenteDto::fromEntity).collect(Collectors.toList()),
                compte.getAchatList().stream().map(AchatDto::fromEntity).collect(Collectors.toList()),
                compte.getTransaction().stream().map(TransactionDto::fromEntity).collect(Collectors.toList()),
                EntrepriseDto.fromEntity(compte.getEntreprise())
        );
    }

    public static Compte toEntity(CompteDto compteDTO) {
        if (compteDTO == null) {
            return null;
        }

        Compte compte = new Compte();
        compte.setId(compteDTO.getId());
        compte.setIsDeleted(compteDTO.getIsDeleted());
        compte.setNomCompte(compteDTO.getNomCompte());
        compte.setCredit(compteDTO.getCredit());
        compte.setVersementList(compteDTO.getVersementList().stream().map(VersementDto::toEntity).collect(Collectors.toList()));
        compte.setVenteList(compteDTO.getVenteList().stream().map(VenteDto::toEntity).collect(Collectors.toList()));
        compte.setAchatList(compteDTO.getAchatList().stream().map(AchatDto::toEntity).collect(Collectors.toList()));
        compte.setTransaction(compteDTO.getTransactionList().stream().map(TransactionDto::toEntity).collect(Collectors.toList()));
        compte.setEntreprise(EntrepriseDto.toEntity(compteDTO.getEntreprise()));

        return compte;
    }
}
