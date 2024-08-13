package com.stockManagment.api.versement;

import com.stockManagment.api.compte.CompteDto;
import com.stockManagment.api.dette.Dette;
import com.stockManagment.api.dette.DetteDto;
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
    private DetteDto dette;

    public static VersementDto fromEntity(Versement versement) {
        if (versement == null) {
            return null;
        }

        return new VersementDto(
                versement.getId(),
                versement.getTypeVersement(),
                versement.getSome(),
                CompteDto.fromEntity(versement.getCompte()),
                DetteDto.fromEntity(versement.getDette())
        );
    }

    public static Versement toEntity(VersementDto versementDto) {
        if (versementDto == null) {
            return null;
        }

        Versement versement = new Versement();
        versement.setId(versementDto.getId());
        versement.setTypeVersement(versementDto.getTypeDette());
        versement.setSome(versementDto.getSome());
        versement.setCompte(CompteDto.toEntity(versementDto.getCompte()));
        versement.setDette(DetteDto.toEntity(versementDto.getDette()));

        return versement;
    }
}
