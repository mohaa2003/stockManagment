package com.stockManagment.api.versement;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.DetteFournisseurDto;
import com.stockManagment.api.dette.DetteType;
import com.stockManagment.api.fournisseur.FournisseurDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VersementFournisseurDto extends VersementDto {
    private DetteFournisseurDto detteFournisseur;
    private FournisseurDto fournisseur;

    public VersementFournisseurDto(Integer id, DetteType typeDette, Double some, CompteDto compte, DetteFournisseurDto detteFournisseur, FournisseurDto fournisseur) {
        super(id, typeDette, some, compte);
        this.detteFournisseur = detteFournisseur;
        this.fournisseur = fournisseur;
    }

    public static VersementFournisseurDto fromEntity(VersementFournisseur entity) {
        if (entity == null) {
            return null;
        }
        return new VersementFournisseurDto(
                entity.getId(),
                entity.getTypeDette(),
                entity.getSome(),
                CompteDto.fromEntity(entity.getCompte()),
                DetteFournisseurDto.fromEntity(entity.getDetteFournisseur()),
                FournisseurDto.fromEntity(entity.getFournisseur())
        );
    }

    public static VersementFournisseur toEntity(VersementFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        VersementFournisseur entity = new VersementFournisseur();
        entity.setId(dto.getId());
        entity.setTypeDette(dto.getTypeDette());
        entity.setSome(dto.getSome());
        entity.setCompte(CompteDto.toEntity(dto.getCompte()));
        entity.setDetteFournisseur(DetteFournisseurDto.toEntity(dto.getDetteFournisseur()));
        entity.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
        return entity;
    }
}

