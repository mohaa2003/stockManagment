package com.stockManagment.api.dette;

import com.stockManagment.api.entreprise.EntrepriseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetteDto {
    private Integer id;
    private double some;
    private EntrepriseDto entreprise;

    public static DetteDto fromEntity(Dette dette) {
        if (dette == null) {
            return null;
        }

        return new DetteDto(
                dette.getId(),
                dette.getSome(),
                EntrepriseDto.fromEntity(dette.getEntreprise())
        );
    }

    public static Dette toEntity(DetteDto detteDTO) {
        if (detteDTO == null) {
            return null;
        }

        Dette dette = new Dette();
        dette.setId(detteDTO.getId());
        dette.setSome(detteDTO.getSome());
        dette.setEntreprise(EntrepriseDto.toEntity(detteDTO.getEntreprise()));

        return dette;
    }
}

