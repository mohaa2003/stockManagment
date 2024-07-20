package com.stockManagment.api.entreprise;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.agent.AgentDto;
import com.stockManagment.api.client.ClientDto;
import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.DetteDto;
import com.stockManagment.api.fournisseur.FournisseurDto;
import com.stockManagment.api.produit.ProduitDto;
import com.stockManagment.api.transaction.TransactionDto;
import com.stockManagment.api.utilisateur.UtilisateurDto;
import com.stockManagment.api.vente.VenteDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrepriseDto {
    private Integer id;
    private String nomEntreprise;
    private List<UtilisateurDto> utilisateurList;
    private List<ProduitDto> produitList;
    private List<FournisseurDto> fournisseurList;
    private List<ClientDto> clientList;
    private List<AgentDto> agentList;
    private List<VenteDto> venteList;
    private List<AchatDto> achatList;
    private List<TransactionDto> transactionList;
    private List<CompteDto> compteList;
    private List<DetteDto> detteList;

    public static EntrepriseDto fromEntity(Entreprise entreprise) {
        if (entreprise == null) {
            return null;
        }

        return new EntrepriseDto(
                entreprise.getId(),
                entreprise.getNomEntreprise(),
                entreprise.getUtilisateurList().stream().map(UtilisateurDto::fromEntity).collect(Collectors.toList()),
                entreprise.getProduitList().stream().map(ProduitDto::fromEntity).collect(Collectors.toList()),
                entreprise.getFournisseurList().stream().map(FournisseurDto::fromEntity).collect(Collectors.toList()),
                entreprise.getClientList().stream().map(ClientDto::fromEntity).collect(Collectors.toList()),
                entreprise.getAgentList().stream().map(AgentDto::fromEntity).collect(Collectors.toList()),
                entreprise.getVenteList().stream().map(VenteDto::fromEntity).collect(Collectors.toList()),
                entreprise.getAchatList().stream().map(AchatDto::fromEntity).collect(Collectors.toList()),
                entreprise.getTransactionList().stream().map(TransactionDto::fromEntity).collect(Collectors.toList()),
                entreprise.getCompteList().stream().map(CompteDto::fromEntity).collect(Collectors.toList()),
                entreprise.getDetteList().stream().map(DetteDto::fromEntity).collect(Collectors.toList())
        );
    }

    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        if (entrepriseDto == null) {
            return null;
        }

        Entreprise entreprise = new Entreprise();
        entreprise.setId(entrepriseDto.getId());
        entreprise.setNomEntreprise(entrepriseDto.getNomEntreprise());
        // Conversion des listes d'entités à partir de Dtos n'est généralement pas nécessaire

        return entreprise;
    }
}
