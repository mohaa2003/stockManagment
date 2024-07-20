package com.stockManagment.api.dette;

import com.stockManagment.api.entreprise.EntrepriseDto;
import com.stockManagment.api.fournisseur.FournisseurDto;
import com.stockManagment.api.versement.VersementFournisseurDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class DetteFournisseurDto extends DetteDto {
    private List<VersementFournisseurDto> versementFournisseurList;
    private FournisseurDto fournisseur;

    public DetteFournisseurDto(Integer id, DetteType typeDette, Double some, EntrepriseDto entreprise, List<VersementFournisseurDto> versementFournisseurList, FournisseurDto fournisseur) {
        super(id, typeDette, some, entreprise);
        this.versementFournisseurList = versementFournisseurList;
        this.fournisseur = fournisseur;
    }

    public static DetteFournisseurDto fromEntity(DetteFournisseur entity) {
        if (entity == null) {
            return null;
        }
        return new DetteFournisseurDto(
                entity.getId(),
                entity.getTypeDette(),
                entity.getSome(),
                EntrepriseDto.fromEntity(entity.getEntreprise()),
                entity.getVersementFournisseurList().stream().map(VersementFournisseurDto::fromEntity).collect(Collectors.toList()),
                FournisseurDto.fromEntity(entity.getFournisseur())
        );
    }

    public static DetteFournisseur toEntity(DetteFournisseurDto dto) {
        if (dto == null) {
            return null;
        }
        DetteFournisseur entity = new DetteFournisseur();
        entity.setId(dto.getId());
        entity.setTypeDette(dto.getTypeDette());
        entity.setSome(dto.getSome());
        entity.setEntreprise(EntrepriseDto.toEntity(dto.getEntreprise()));
        entity.setVersementFournisseurList(dto.getVersementFournisseurList().stream().map(VersementFournisseurDto::toEntity).collect(Collectors.toList()));
        entity.setFournisseur(FournisseurDto.toEntity(dto.getFournisseur()));
        return entity;
    }
}

