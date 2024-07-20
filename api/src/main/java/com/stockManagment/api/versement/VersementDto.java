package com.stockManagment.api.versement;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.DetteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VersementDto {
    private Integer id;
    private DetteType typeDette;
    private Double some;
    private CompteDto compte;

    public static VersementDto fromEntity(Versement versement) {
        if (versement == null) {
            return null;
        }

        return new VersementDto(
                versement.getId(),
                versement.getTypeDette(),
                versement.getSome(),
                CompteDto.fromEntity(versement.getCompte())
        );
    }

    public static Versement toEntity(VersementDto versementDto) {
        if (versementDto == null) {
            return null;
        }

        Versement versement = new Versement();
        versement.setId(versementDto.getId());
        versement.setTypeDette(versementDto.getTypeDette());
        versement.setSome(versementDto.getSome());
        versement.setCompte(CompteDto.toEntity(versementDto.getCompte()));

        return versement;
    }
}
