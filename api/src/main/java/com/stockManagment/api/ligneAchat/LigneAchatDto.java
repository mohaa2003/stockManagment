package com.stockManagment.api.ligneAchat;

import com.stockManagment.api.achat.AchatDto;
import com.stockManagment.api.produit.ProduitDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneAchatDto {
    private Integer id;
    private Integer quantite;
    private Double prixAchat;
    private AchatDto achat;
    private ProduitDto produit;

    public static LigneAchatDto fromEntity(LigneAchat ligneAchat) {
        if (ligneAchat == null) {
            return null;
        }

        return new LigneAchatDto(
                ligneAchat.getId(),
                ligneAchat.getQuantite(),
                ligneAchat.getPrixAchat(),
                AchatDto.fromEntity(ligneAchat.getAchat()),
                ProduitDto.fromEntity(ligneAchat.getProduit())
        );
    }

    public static LigneAchat toEntity(LigneAchatDto ligneAchatDto) {
        if (ligneAchatDto == null) {
            return null;
        }

        LigneAchat ligneAchat = new LigneAchat();
        ligneAchat.setId(ligneAchatDto.getId());
        ligneAchat.setQuantite(ligneAchatDto.getQuantite());
        ligneAchat.setPrixAchat(ligneAchatDto.getPrixAchat());
        ligneAchat.setAchat(AchatDto.toEntity(ligneAchatDto.getAchat()));
        ligneAchat.setProduit(ProduitDto.toEntity(ligneAchatDto.getProduit()));

        return ligneAchat;
    }

    public Double calculPrixLigne(){
        return Double.valueOf(this.quantite * this.prixAchat);
    }
}
