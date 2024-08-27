package com.stockManagment.api.versement;

import com.stockManagment.api.compte.CompteDto;
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
    private Boolean isDeleted;
    private DetteType typeVersement;
    //le versement ici doit etre positive toujour !
    private Double some;
    private CompteDto compte;
    private DetteDto dette;
    private String comment;  //le commentaire pour pouvoir dire quelque chose par exemple le cas de finir les dettes de quelqu'un

    public static VersementDto fromEntity(Versement versement) {
        if (versement == null) {
            return null;
        }

        return new VersementDto(
                versement.getId(),
                versement.getIsDeleted(),
                versement.getTypeVersement(),
                versement.getSome(),
                CompteDto.fromEntity(versement.getCompte()),
                DetteDto.fromEntity(versement.getDette()),
                versement.getComment()
        );
    }

    public static Versement toEntity(VersementDto versementDto) {
        if (versementDto == null) {
            return null;
        }

        Versement versement = new Versement();
        versement.setId(versementDto.getId());
        versement.setIsDeleted(versementDto.isDeleted);
        versement.setTypeVersement(versementDto.getTypeVersement());
        versement.setSome(versementDto.getSome());
        versement.setCompte(CompteDto.toEntity(versementDto.getCompte()));
        versement.setDette(DetteDto.toEntity(versementDto.getDette()));
        versement.setComment(versementDto.getComment());

        return versement;
    }
}
